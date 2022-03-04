package kukukode.progem.project_mcv2.repo;

import kukukode.progem.project_mcv2.entity.ProjectEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProjectRepo extends ReactiveCrudRepository<ProjectEntity,Integer> {
}
