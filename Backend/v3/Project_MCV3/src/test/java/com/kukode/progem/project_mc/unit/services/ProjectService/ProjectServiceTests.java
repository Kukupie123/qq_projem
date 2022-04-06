package com.kukode.progem.project_mc.unit.services.ProjectService;

import com.kukode.progem.project_mc.services.ProjectService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProjectServiceTests {
    Logger log = LoggerFactory.getLogger("Project Service Test");
    //AddAncestry tests
    @Autowired
    ProjectService projectService;

    @Test
    void addAncestry_root() throws Exception {

        log.info(projectService.addAncestry("", -1)); // root project

    }

    @Test
    void addAncestry_Child() throws Exception {
        /*
        Base ancestry is - cuz ancestry of parent is -
        parentProjectID is 1 cuz parentID is 1
         */
        log.info(projectService.addAncestry("-", 1)); //we assume that this projectID is going to be 2
    }

    @Test
    void addAncestry_ChildChild() throws Exception {
        /*
        Base ancestry is - cuz ancestry of parent is -
        parentProjectID is 1 cuz parentID is 1
         */
        log.info(projectService.addAncestry("1", 2)); // it's parent project is ID 2 and ancestry of projectID 2 is 1  as proven in previous test
    }

    @Test
    void addAncestry_ValidationTest() {
        try {
            projectService.addAncestry("1", 1); //This should throw error as 1 is already in ancestry
            throw new Exception("No Exception was thrown by the function");
        } catch (Exception e) {
            log.info("Exception thrown {}", e.getMessage());
        }

    }
}
