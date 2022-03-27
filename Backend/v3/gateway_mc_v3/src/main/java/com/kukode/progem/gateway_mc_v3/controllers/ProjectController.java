package com.kukode.progem.gateway_mc_v3.controllers;

import com.kukode.progem.gateway_mc_v3.models.BaseResponse;
import com.kukode.progem.gateway_mc_v3.models.entities.Project;
import com.kukode.progem.gateway_mc_v3.models.requests.CreateProject;
import com.kukode.progem.gateway_mc_v3.services.AuthService;
import com.kukode.progem.gateway_mc_v3.services.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/" + "${gateway.project.base}")
public class ProjectController {
    Logger log = LoggerFactory.getLogger("Project Controller");
    final AuthService authService;
    final ProjectService projectService;

    public ProjectController(AuthService authService, ProjectService projectService) {
        this.authService = authService;
        this.projectService = projectService;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Mono<ResponseEntity<BaseResponse<Project>>> getProject(@RequestHeader("Authorization") String token, @PathVariable(value = "id") String projectID) {
        return Mono.just(ResponseEntity.ok(new BaseResponse<>(null, "WIP")));
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Mono<ResponseEntity<BaseResponse<Project>>> createProject(@RequestBody CreateProject projectRequest, @RequestHeader("Authorization") String token) {
        log.info("Create Project triggered with body {} and token {}", projectRequest, token);


        if (projectRequest.getTitle() == null || projectRequest.getVisibility() == null)
            return Mono.just(ResponseEntity.internalServerError().build());
        if (projectRequest.getTitle().isEmpty() || projectRequest.getVisibility().isEmpty())
            return Mono.just(ResponseEntity.internalServerError().build());

        /*
        1. Verify if token is valid
        2. If Valid Check Ancestry and decide if it is a root project OR a sub project
        3. If it is a root project, Get the correct RuleID and create a project based on request body and userID from token
        4. If it is not a root project, Check if user is the creator of the main project, if he is, he is allowed to do so
        5. If user is not the creator of the main project, ancestry must not be null and will be used to get it's parent project
        6. We will now check if parent is allowed to create child project and we will also check if user is a leader of the parent project
         */

        //1.Verify token
        return authService.getUserIDFromJWTToken(token.replace("Bearer ", ""))
                .flatMap(baseResponseResponseEntity -> {
                    if (baseResponseResponseEntity.getStatusCode() != HttpStatus.OK) {
                        //JWT token verification failed
                        //So we return Response entity where project is null and message is the error message
                        return Mono.just(ResponseEntity.status(baseResponseResponseEntity.getStatusCode())
                                .body(new BaseResponse<Project>(null, baseResponseResponseEntity.getBody().getMessage())));
                    }
                    //Token is verified and we can proceed
                    var userID = baseResponseResponseEntity.getBody().getData();
                    projectRequest.setUserID(userID);
                    return projectService.createProject(projectRequest);
                });

    }
}
