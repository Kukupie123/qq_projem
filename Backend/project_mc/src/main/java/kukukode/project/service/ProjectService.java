package kukukode.project.service;

import kukukode.project.jpa.ProjectEntity;
import kukukode.project.reqresp.CreateProjectRequest;

public interface ProjectService {
    /**
     * Adds new record to projects table
     *
     * @param projectEntity
     */
    void createNewProject(ProjectEntity projectEntity);


    /**
     * Creates an "ProjectEntity" object out of "CreateProjectRequest" object.
     * Use this method to get a ProjectEntity object when adding a new project in database
     *
     * @param request
     * @return
     */
    ProjectEntity createProjectEntity_From_CurrentProjectRequest(CreateProjectRequest request) throws Exception;

    /**
     * Checks if the rules supplied is valid, for a rule to be valid it must have all access same or lower than it's parent
     *
     * @param rule the string in json format
     * @return
     */
    boolean isRuleValid(String rule);
}
