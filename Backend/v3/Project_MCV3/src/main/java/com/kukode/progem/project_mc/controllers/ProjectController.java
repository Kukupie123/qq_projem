package com.kukode.progem.project_mc.controllers;

import com.kukode.progem.project_mc.models.BaseResponse;
import com.kukode.progem.project_mc.models.entities.Project;
import com.kukode.progem.project_mc.models.entities.ProjectRuleEntity;
import com.kukode.progem.project_mc.models.requests.CreateProject;
import com.kukode.progem.project_mc.services.MemberService;
import com.kukode.progem.project_mc.services.ProjectService;
import com.kukode.progem.project_mc.services.RuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.Instant;

@RestController
@RequestMapping("/" + "${project.base}")
public class ProjectController {
    final Logger log = LoggerFactory.getLogger("Project Controller");

    final ProjectService projectService;
    final RuleService ruleService;
    final MemberService memberService;

    public ProjectController(ProjectService projectService, RuleService ruleService, MemberService memberService) {
        this.projectService = projectService;
        this.ruleService = ruleService;
        this.memberService = memberService;
    }


    public Mono<ResponseEntity<BaseResponse<Project>>> getProject() {
        return null;
    }

    /*
    Validation of the payload is accompanied with check if the project is a root project or a child project.
    If root project the requesterID has to be the same with toBeLeaderID since it only makes sense for the creator of the project to be the leader itself.
    If it's hard to grasp take this analogy as example : If you buy a car you expect yourself to be the owner of the car, likewise if you create a project
    you expect yourself being the leader of the project.

    If not root project. Get the parent project based on ancestry.
    Check if parent project exist and it's rule allows creating child project && check if requesterID is a leader of parent project

     */
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public Mono<ResponseEntity<BaseResponse<Project>>> createProject(@RequestBody CreateProject projectReq) {

        log.info("Create project triggered with body {}", projectReq);
        //**Validating the payload**
        if (projectReq.getTitle().trim().isEmpty() ||
                projectReq.getVisibility().trim().isEmpty())
            return Mono.just(ResponseEntity.badRequest().body(new BaseResponse<Project>(null, "Invalid payload")));

        boolean isRootProject = projectReq.getParentProjectID() < 0; //if parentProjectID is less than zero we know that it is a parent project

        if (isRootProject) {
            log.info("Creating root project");
            //Check if to be leader AND requesterID is the same. They need to be the same to be able to proceed further
            String requesterID = projectReq.getRequesterID();
            String toBeLeaderID = projectReq.getTobeLeaderID();
            if (requesterID == null || requesterID.trim().isEmpty() || !requesterID.equalsIgnoreCase(toBeLeaderID))
                //Requester and ToBeLeader is not the same, return error
                return Mono.just(ResponseEntity.badRequest().body(new BaseResponse<Project>(null, "requesterID is not defined or requesterID doesn't match tobeleaderID")));

            //Requester and ToBeLeader are same and we can proceed

            //Create Rule with Max privilege
            ProjectRuleEntity createdProjectRule;
            try {
                createdProjectRule = projectService.createRootProjectRule(projectReq.getVisibility());
            } catch (Exception e) {
                return Mono.just(ResponseEntity.badRequest().body(new BaseResponse<Project>(null, e.getMessage())));
            }
            //Get the correct rule Id from Rule MC
            var ruleIDMono = ruleService.getSimilarRule(createdProjectRule);

            return ruleIDMono
                    .flatMap(response -> {
                                var ruleID = response.getBody();
                                if (!response.getStatusCode().is2xxSuccessful())
                                    return Mono.just(ResponseEntity
                                            .status(response.getStatusCode()
                                            ).body(new BaseResponse<Project>(null, response.getBody().getMessage())));

                                //ruleID is ok so now we can create Project, add the user as the leader and return back the project

                                //Create the project entity
                                Project project = new Project();
                                project.setTimestamp(Timestamp.from(Instant.now()));
                                project.setTitle(projectReq.getTitle());
                                project.setIscomplete(false);
                                project.setDescription(projectReq.getDesc());
                                project.setRulesid(ruleID.getData());
                                try {
                                    project.setAncestry(projectService.addAncestry("", -1)); //-1 Signifies that it is a parent project with no parent
                                } catch (Exception e) {
                                    return Mono.just(ResponseEntity.badRequest().body(new BaseResponse<Project>(null, e.getMessage())));
                                }
                                project.setUserid(projectReq.getRequesterID());
                                log.info("Created new project and sending it to projectService.createProject()  : {}", project);
                                return projectService.createProject(project, projectReq.getRequesterID(), projectReq.getTobeLeaderID());

                            }
                    );


        } else {
            //A sub project creation request

            //1. Get parent project based on parentID of payload

            int parentID = projectReq.getParentProjectID();

            //2. Get parent project
            Mono<Project> parentProjectMono = projectService.getProject(parentID);

            return parentProjectMono
                    .flatMap(parentProject -> {
                                if (parentProject.getId() < 0) {
                                    //We did not find the project
                                    return Mono.just(ResponseEntity.status(404).body(new BaseResponse<Project>(null, "Parent Project not found")));
                                }
                                //Project found

                                //3. Get rules of parent based on RulesID
                                Mono<ResponseEntity<BaseResponse<ProjectRuleEntity>>> projectRuleResponseMono = ruleService.getRule(parentProject.getRulesid());

                                return projectRuleResponseMono
                                        .flatMap(projectRuleResponse -> {
                                                    if (!projectRuleResponse.getStatusCode().is2xxSuccessful())
                                                        return Mono.just(ResponseEntity.status(projectRuleResponse.getStatusCode()).body(new BaseResponse<Project>(null, projectRuleResponse.getBody().getMessage())));

                                                    //We got the rule successfully

                                                    //4. Check if rules allow creation of child project
                                                    ProjectRuleEntity parentProjectRule = projectRuleResponse.getBody().getData();
                                                    if (!parentProjectRule.isHave_children()) {
                                                        //NOT ALLOWED TO HAVE CHILDREN
                                                        return Mono.just(ResponseEntity.internalServerError().body(new BaseResponse<Project>(null, "No permission to create Sub project as defined by rule of parent project")));
                                                    }
                                                    //Allowed to have children
                                                    if (parentProjectRule.isHave_children_needs_permission()) {
                                                        //TODO: Permission needed from root leader i.e pending project request stuff
                                                        //return Mono.just(ResponseEntity.status(404).body(new BaseResponse<Project>(null, "Permission needed from root leader. WIP")));
                                                    }
                                                    //Allowed to create child project without root permission

                                                    //5. Validate the rules sent in payload
                                                    if (projectReq.getProjectRule() == null)
                                                        return Mono.just(ResponseEntity.badRequest().body(new BaseResponse<Project>(null, "Rule is null for the project you are trying to create")));
                                                    //Project rule is valid
                                                    ProjectRuleEntity childProjectRule = projectReq.getProjectRule();
                                                    //6. Validate the rule using Rule MC which will return a rule which has max privilege as parent IN CASE child has more privilege than parent in some case
                                                    Mono<ResponseEntity<BaseResponse<ProjectRuleEntity>>> newChildRuleMono = ruleService.validateRule(parentProjectRule, childProjectRule);

                                                    return newChildRuleMono.flatMap(baseResponseResponseEntity -> {
                                                                if (!baseResponseResponseEntity.getStatusCode().is2xxSuccessful())
                                                                    //We failed to get a good rule response back from the RuleMC
                                                                    return Mono.just(ResponseEntity.internalServerError().body(new BaseResponse<Project>(null, baseResponseResponseEntity.getBody().getMessage())));

                                                                //got child rule successfully
                                                                //6. Get similar rule for the project
                                                                ProjectRuleEntity newChildProjectRule = baseResponseResponseEntity.getBody().getData();
                                                                Mono<ResponseEntity<BaseResponse<Integer>>> projectRuleID = ruleService.getSimilarRule(newChildProjectRule);
                                                                return projectRuleID.flatMap(projectIDResponse -> {
                                                                            if (!projectIDResponse.getStatusCode().is2xxSuccessful())
                                                                                //Failed to get similar rule from RUleMC
                                                                                return Mono.just(ResponseEntity.internalServerError().body(new BaseResponse<Project>(null, projectIDResponse.getBody().getMessage())));

                                                                            int childRuleID = projectIDResponse.getBody().getData();
                                                                            //7.Got ruleID successfully. Now we need to create a project entity and save it
                                                                            //Create the project entity
                                                                            Project childProject = new Project();
                                                                            childProject.setTimestamp(Timestamp.from(Instant.now()));
                                                                            childProject.setTitle(projectReq.getTitle());
                                                                            childProject.setIscomplete(false);
                                                                            childProject.setDescription(projectReq.getDesc());
                                                                            childProject.setRulesid(childRuleID);
                                                                            try {
                                                                                childProject.setAncestry(projectService.addAncestry(parentProject.getAncestry(), parentProject.getId()));
                                                                            } catch (Exception e) {
                                                                                return Mono.just(ResponseEntity.badRequest().body(new BaseResponse<Project>(null, e.getMessage())));
                                                                            }
                                                                            childProject.setUserid(projectReq.getRequesterID());
                                                                            //Create project using the projectService which will also add the leader
                                                                            return projectService.createProject(childProject, projectReq.getRequesterID(), projectReq.getTobeLeaderID());

                                                                        }
                                                                );

                                                            }
                                                    );


                                                }
                                        );
                            }
                    );
        }
    }
}
