package com.kukode.progem.member_mc.repo;

import com.kukode.progem.member_mc.models.entities.Project;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ProjectRepo extends ReactiveCrudRepository<Project,Integer> {
}
