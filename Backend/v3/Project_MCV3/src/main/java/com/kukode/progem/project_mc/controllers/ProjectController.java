package com.kukode.progem.project_mc.controllers;

import com.kukode.progem.project_mc.models.BaseResponse;
import com.kukode.progem.project_mc.models.entities.Project;
import com.kukode.progem.project_mc.models.requests.CreateProject;
import com.kukode.progem.project_mc.services.MemberService;
import com.kukode.progem.project_mc.services.ProjectService;
import com.kukode.progem.project_mc.services.RuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/" + "${project.base}")
public class ProjectController {

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

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public Mono<ResponseEntity<BaseResponse<Project>>> createProject(@RequestBody CreateProject projectReq) {
        //**Validating the payload**
        if (
                !(
                        projectReq.getTitle().trim().isEmpty() ||
                                projectReq.getVisibility().trim().isEmpty()

                )
        )
            return Mono.just(ResponseEntity.badRequest().body(new BaseResponse<Project>(null, "Invalid payload")));

        /*
        TODO:
        1. Check if user is allowed to create project(Can create if user is root project leader OR leader of parent Project&Parent project has rule enabled for creating child)
        2. Assign correct RuleID
        3. If Root project, make userID the root leader
         */

        boolean isRootProject = projectReq.getAncestry() == null || projectReq.getAncestry().trim().isEmpty();

        if (isRootProject) {
            //No need to check if user is allowed to create as it is the main project not a sub project
            String userID = projectReq.getUserID();
            if (userID == null || userID.trim().isEmpty())
                return Mono.just(ResponseEntity.badRequest().body(new BaseResponse<Project>(null, "userID is not defined")));

            //Create Rule with Max privilege
            var createdProjectRule = projectService.createRootProjectRule(projectReq.getVisibility());
            //Get the correct rule Id from Rule MC
            var ruleIDMono = ruleService.getSimilarRule(createdProjectRule);

            return ruleIDMono
                    .flatMap(response -> {
                        var ruleID = response.getBody();
                        if (!response.getStatusCode().is2xxSuccessful())
                            return Mono.just(ResponseEntity
                                    .status(response.getStatusCode()
                                    ).body(new BaseResponse<>(-1, response.getBody().getMessage())));

                        //ruleID is ok so now we can create Project, add the user as the leader and return back the project

                        //create project
                        //Create the project entity
                        Project project = new Project();
                        project.setTimestamp(new Date(System.currentTimeMillis()));
                        project.setTitle(projectReq.getTitle());
                        project.setIscomplete(false);
                        project.setDescription(projectReq.getDesc());
                        project.setRulesid(ruleID.getData());
                        project.setAncestry("");

                        var savedProjectMono = projectService.createProject(project);

                        return savedProjectMono.flatMap(savedProject -> {
                            //Created project successfully Now we need to save the user as the leader
                            var leaderMono = memberService.addLeader(savedProject.getId());
                            return leaderMono.map(memberRes -> {
                                if (!memberRes.getStatusCode().is2xxSuccessful())
                                    return ResponseEntity.status(memberRes.getStatusCode()).body(new BaseResponse<Project>(null, memberRes.getBody().getMessage()));
                                return ResponseEntity.ok(new BaseResponse<Project>(savedProject, "successfully created project, and added user as leader"));
                            });
                        });
                    });


        } else {
            //Check if user is Root OR leader of parent Project AND parent project is allowed to create child
        }


        return null;
    }
}
