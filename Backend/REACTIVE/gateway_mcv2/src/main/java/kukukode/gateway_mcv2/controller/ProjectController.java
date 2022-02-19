package kukukode.gateway_mcv2.controller;


import kukukode.gateway_mcv2.entities.ProjectEntity;
import kukukode.gateway_mcv2.entities.ProjectRuleEntity;
import kukukode.gateway_mcv2.req_resp_model.ReqModelCreateRootProject;
import kukukode.gateway_mcv2.service.microservice.rules.projectrule.ProjectRuleMCService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/project")
public class ProjectController {

    final
    ProjectRuleMCService projectRuleMCService;

    public ProjectController(ProjectRuleMCService projectRuleMCService) {
        this.projectRuleMCService = projectRuleMCService;
    }

    //Project MC expects title,desc,userID in authHeader
    //Expects Title,Desc,Visibility, authHeader
    @PostMapping("")
    public Mono<ResponseEntity<ProjectEntity>> createRootProject(@RequestBody ReqModelCreateRootProject request) {
        //Send it to Project MC who will create it for us

        //Create Root rule with max privilege
        ProjectRuleEntity rule = projectRuleMCService.createRootProjectRule(request.getVisibility());

        //Send rule to Rule MC and it will send ID of rule if such rule exist or else create one and return it's ID
        var a = projectRuleMCService.createRuleIfNotExist(rule);
        //Create Project entity based on all information we have
        ProjectEntity project = new ProjectEntity();
        project.setAncestry("-");
        project.setDescription(request.getDesc());
        project.setIscomplete(false);
        project.setRulesid(67);
        project.setTimestamp(new Date(System.currentTimeMillis()));
        project.setTitle(request.getTitle());

        return a;


    }
}
