package com.kukode.progem.project_mc.controllers;

import com.kukode.progem.project_mc.models.BaseResponse;
import com.kukode.progem.project_mc.models.entities.Project;
import com.kukode.progem.project_mc.models.requests.CreateProject;
import com.kukode.progem.project_mc.repo.ProjectRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/" + "${project.base}")
public class ProjectController {

    final ProjectRepo projectRepo;

    public ProjectController(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }


    public Mono getProject() {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public Mono<ResponseEntity<BaseResponse<Project>>> createProject(@RequestBody CreateProject projectReq) {
        //Validating the payload
        if (
                !(
                        projectReq.getTitle().trim().isEmpty() ||
                                projectReq.getVisibility().trim().isEmpty() ||
                                projectReq.getTitle().trim().isEmpty()
                )
        )
            return Mono.just(ResponseEntity.badRequest().body(new BaseResponse<Project>(null, "Invalid payload")));

        /*
        TODO:
        1. Check if user is allowed to create project(Can create if user is root project leader OR leader of parent Project&Parent project has rule enabled for creating child)
        2. Assign correct RuleID
        3. If Root project, make userID the root leader
         */

        boolean isRootProject = false;
        if (projectReq.getAncestry() == null || projectReq.getAncestry().trim().isEmpty())
            isRootProject = true;

        if (isRootProject) {
            //No need to check if user is allowed to create as it is the main project not a sub project
            String userID = projectReq.getUserID();
            if (userID == null || userID.trim().isEmpty())
                return Mono.just(ResponseEntity.badRequest().body(new BaseResponse<Project>(null, "userID is not defined")));

            //Get the correct rule Id from Rule MC



        } else {
            //Check if user is Root OR leader of parent Project AND parent project is allowed to create child
        }


        return null;
    }
}
