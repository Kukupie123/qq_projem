package kukukode.project.controllers;


import kukukode.project.reqresp.CreateProjectRequest;
import kukukode.project.reqresp.CreateProjectResponse;
import kukukode.project.service.ProjectServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class MainController {

    final ProjectServiceImp serviceImp;

    @Autowired
    public MainController(ProjectServiceImp serviceImp) {
        this.serviceImp = serviceImp;
    }

    @PostMapping("/createrootproject")
    public ResponseEntity<CreateProjectResponse> createRootProject(@RequestBody CreateProjectRequest request, @RequestHeader("Authorization") String token) throws Exception {
        //Parse the token
        token = token.replace("Bearer ", "");
        //Set the token for ProjectService
        serviceImp.token = token;
        //Create an ProjectEntity from request object
        var entity = serviceImp.createProjectEntity_From_CurrentProjectRequest(request);
        serviceImp.createNewProject(entity);
        return ResponseEntity.ok(new CreateProjectResponse());
    }
}
