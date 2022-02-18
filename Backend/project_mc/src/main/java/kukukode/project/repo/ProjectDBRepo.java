package kukukode.project.repo;

import kukukode.project.jpa.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDBRepo extends CrudRepository<ProjectEntity, Integer> {
}
