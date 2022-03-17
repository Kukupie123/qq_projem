package kukukode.gateway_mcv2.controller;


import kukukode.gateway_mcv2.entities.ProjectEntity;
import kukukode.gateway_mcv2.entities.ProjectRuleEntity;
import kukukode.gateway_mcv2.req_resp_model.ReqModelCreateRootProject;
import kukukode.gateway_mcv2.service.microservice.project.ProjectMCService;
import kukukode.gateway_mcv2.service.microservice.rules.projectrule.ProjectRuleMCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/project")
public class ProjectController {

    final
    ProjectRuleMCService projectRuleMCService;
    final ProjectMCService projectMCService;

    @Autowired
    public ProjectController(ProjectRuleMCService projectRuleMCService, ProjectMCService projectMCService) {
        this.projectRuleMCService = projectRuleMCService;
        this.projectMCService = projectMCService;
    }

    //Project MC expects title,desc,userID in authHeader
    //Expects Title,Desc,Visibility, authHeader
    @PostMapping("/root")
    public Mono<ResponseEntity<ProjectEntity>> createRootProject(@RequestBody ReqModelCreateRootProject request,
                                                                 @RequestHeader("Authorization") String token) {
        /*
        1. Create a rule similar to the rules mentioned (There is max privilege rule for root projects)  if it doesn't exist, this should supply us with the ID of the rule
        2. Create project object based on payload and rulesID
        3. Add the userID as the leader of the root project
         */


        //Create Root rule with max privilege
        ProjectRuleEntity rule = projectRuleMCService.createRootProjectRule(request.getVisibility());

        //Send rule to Rule MC and it will send ID of rule if such rule exist or else create one and return it's ID
        var ruleIDMono = projectRuleMCService.createRuleIfNotExist(rule);


        //Create Project entity based on all information we have
        return ruleIDMono.flatMap(integerResponseEntity ->
                {
                    //Create the project entity
                    ProjectEntity project = new ProjectEntity();
                    project.setTimestamp(new Date(System.currentTimeMillis()));
                    project.setTitle(request.getTitle());
                    project.setIscomplete(false);
                    project.setDescription(request.getDesc());
                    project.setRulesid(integerResponseEntity.getBody());
                    project.setAncestry("-");

                    //Add the user as the root leader

                    //Pass it to Project Microservice and return the project we get
                    return projectMCService.createProject(project, token);
                }
        );


    }
}
