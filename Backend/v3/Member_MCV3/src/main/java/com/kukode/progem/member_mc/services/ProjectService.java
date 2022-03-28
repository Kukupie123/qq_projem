package com.kukode.progem.member_mc.services;

import com.kukode.progem.member_mc.models.entities.Project;
import com.kukode.progem.member_mc.repo.ProjectRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProjectService {

    final ProjectRepo projectRepo;

    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public Mono<Project> getProjectFromID(int id){
        return projectRepo.findById(id);
    }
}
