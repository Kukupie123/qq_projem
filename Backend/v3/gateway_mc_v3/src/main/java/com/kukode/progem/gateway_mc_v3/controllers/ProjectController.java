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

    /*
    Extracts the requesterID from the JWT token. Then checks if the project is private or public.
    If public we simply return it back. If private we check if the requester is part of the project and return it.
    This functionality is going to be mainly done by project Microservice
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Mono<ResponseEntity<BaseResponse<Project>>> getProject(@RequestHeader("Authorization") String token, @PathVariable(value = "id") String projectID) {
        return Mono.just(ResponseEntity.ok(new BaseResponse<>(null, "WIP")));
    }

    /*
    Client will need to send title, visibility and toBeLeader as the mandatory field.
    First step involves validating the payload
    Then we extract the userID from the token and this is going to be the requesterID and forwarded to Project microservice along with the payload
     */
    /**
     * @param projectRequest requesterID : will be extracted from the JWT token, tobeLeaderID : UserID of the user who is going to be the leader of the project
     * @param token          JWT token from "Authorization" bearer token
     * @return Project entity
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Mono<ResponseEntity<BaseResponse<Project>>> createProject(@RequestBody CreateProject projectRequest, @RequestHeader("Authorization") String token) {
        log.info("Create Project triggered with body {} and token {}", projectRequest, token);

        //Validate payload
        if (projectRequest.getTitle() == null || projectRequest.getVisibility() == null || projectRequest.getTobeLeaderID() == null)
            return Mono.just(ResponseEntity.badRequest().body(new BaseResponse<Project>(null, "Title and/or visibility and/or toBeLeaderID is null")));
        if (projectRequest.getTitle().isEmpty() || projectRequest.getVisibility().isEmpty()
                || projectRequest.getVisibility().equalsIgnoreCase("private") == false || projectRequest.getVisibility().equalsIgnoreCase("public") == false
                || projectRequest.getTobeLeaderID().trim().isEmpty())
            return Mono.just(ResponseEntity.badRequest().body(new BaseResponse<Project>(null, "Title and/or visibility(needs to be 'public' or 'private') and/or toBeLeaderID is invalid")));

        //1.Verify token
        return authService.getUserIDFromJWTToken(token.replace("Bearer ", ""))
                .flatMap(baseResponseResponseEntity -> {
                            if (baseResponseResponseEntity.getStatusCode() != HttpStatus.OK) {
                                log.info("JWT verification failed with message {}", baseResponseResponseEntity.getBody().getMessage());
                                return Mono.just(ResponseEntity.status(baseResponseResponseEntity.getStatusCode())
                                        .body(new BaseResponse<Project>(null, baseResponseResponseEntity.getBody().getMessage())));
                            }
                            //Token is verified and we can proceed
                            var userID = baseResponseResponseEntity.getBody().getData();
                            log.info("JWT verified, userID : {}", userID);
                            projectRequest.setRequesterID(userID);
                            return projectService.createProject(projectRequest);
                        }
                );
    }
}
