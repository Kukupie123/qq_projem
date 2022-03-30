package com.kukode.progem.member_mc.repo;

import com.kukode.progem.member_mc.models.entities.Project;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProjectRepo extends ReactiveCrudRepository<Project,Integer> {

}
