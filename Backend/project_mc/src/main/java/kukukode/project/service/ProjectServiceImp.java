package kukukode.project.service;

import kukukode.project.jpa.ProjectEntity;
import kukukode.project.repo.ProjectDBRepo;
import kukukode.project.reqresp.CreateProjectRequest;
import kukukode.project.service.microservice.JWTMCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProjectServiceImp implements ProjectService {
    final ProjectDBRepo repo;
    final JWTMCService jwtmcService;
    public String token;

    @Autowired
    public ProjectServiceImp(ProjectDBRepo repo, JWTMCService jwtmcService) {
        this.repo = repo;
        this.jwtmcService = jwtmcService;
    }

    @Override
    public void createNewProject(ProjectEntity projectEntity) {
        repo.save(projectEntity);
    }

    @Override
    public ProjectEntity createProjectEntity_From_CurrentProjectRequest(CreateProjectRequest request) throws Exception {
        ProjectEntity entity = new ProjectEntity();
        entity.setCreatedAt(new Date(System.currentTimeMillis()));
        entity.setAncestry(request.getAncestry());
        entity.setComplete(false);
        entity.setDesc(request.getDesc());
        entity.setTitle(request.getTitle());
        /*
        TODO: READ ME CAREFULLY
        Rules are going to be customized in the client, they can modify rules and give themselves special privileges which should not be possible
        Hence why we need to compare it with the parent's rule and see if the rules are valid.
        This will be very crucial step when creating sub projects
         */
        entity.setRulesID(12);
        entity.setUserID(jwtmcService.getUserID(token));
        return entity;
    }

    @Override
    public boolean isRuleValid(String rule) {
        return true;
    }
}
