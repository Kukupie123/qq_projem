package com.kukode.progem.member_mc.services;

import com.kukode.progem.member_mc.models.entities.Project;
import com.kukode.progem.member_mc.repo.ProjectRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {
    final Logger log = LoggerFactory.getLogger("Project Service");

    final ProjectRepo projectRepo;

    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public Mono<Project> getProjectFromID(int id) {

        return projectRepo.findById(id)
                .flatMap(Mono::just);
    }

    /**
     * Gets the parent from the ancestry
     *
     * @param ancestry the ancestry of the project
     * @return projectID of the parent
     */
    public int getParentBasedOnAncestry(String ancestry) throws Exception {
        if (ancestry.isEmpty()) throw new Exception("Ancestry is empty");
        String[] parents = ancestry.split("-");
        if (parents.length <= 0) throw new Exception("No parent found in ancestry");
        return Integer.parseInt(parents[parents.length - 1]);

    }


    public String getRootProjectFromAncestry(String ancestry) {

        var ancestries = ancestry.split("-");
        List<String> ancestriesList = new ArrayList<String>(Arrays.asList(ancestries));
        ancestriesList.removeIf(String::isEmpty);
        return ancestriesList.get(0);

    }
}
