package kukukode.progem.project_mcv2.service;

import kukukode.progem.project_mcv2.entity.ProjectEntity;
import kukukode.progem.project_mcv2.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProjectService {
    final ProjectRepo projectRepo;

    @Autowired
    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public Mono<ProjectEntity> createRootProject(ProjectEntity projectEntity) {
        return projectRepo.save(projectEntity)
                .flatMap(Mono::just);
    }
}
