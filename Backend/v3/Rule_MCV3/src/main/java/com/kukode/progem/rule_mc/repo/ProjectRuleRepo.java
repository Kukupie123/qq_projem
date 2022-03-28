package com.kukode.progem.rule_mc.repo;

import com.kukode.progem.rule_mc.models.entities.ProjectRuleEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProjectRuleRepo extends ReactiveCrudRepository<ProjectRuleEntity, Integer>, ReactiveQueryByExampleExecutor<ProjectRuleEntity> {
    @Query("SELECT * FROM projectrules WHERE visibility = :visibility AND child_visibilities_allowed = :child_visibilities_allowed AND have_children = :have_children AND have_children_needs_permission = :have_children_needs_permission AND can_leader_task_member = :can_leader_task_member AND task_member_visibilities_allowed = :task_member_visibilities_allowed AND task_member_needs_permission = :task_member_needs_permission AND task_member_complete_permission = :task_member_complete_permission AND can_leader_task_children = :can_leader_task_children AND task_child_visibilities_allowed = :task_child_visibilities_allowed  AND task_child_needs_permission = :task_child_needs_permission AND task_child_complete_permission = :task_child_complete_permission AND can_leader_task_foreign = :can_leader_task_foreign AND task_foreign_visibilities_allowed = :task_foreign_visibilities_allowed AND task_foreign_needs_permission = :task_foreign_needs_permission AND task_foreign_complete_permission = :task_foreign_complete_permission")
    Mono<ProjectRuleEntity> findSimilarRule(
            @Param("visibility") String visibility,
            @Param("child_visibilities_allowed") String child_visibilities_allowed,
            @Param("have_children") boolean have_children,
            @Param("have_children_needs_permission") boolean have_children_needs_permission,
            @Param("can_leader_task_member") boolean can_leader_task_member,
            @Param("task_member_visibilities_allowed") String task_member_visibilities_allowed,
            @Param("task_member_needs_permission") boolean task_member_needs_permission,
            @Param("task_member_complete_permission") boolean task_member_complete_permission,
            @Param("can_leader_task_children") boolean can_leader_task_children,
            @Param("task_child_visibilities_allowed") String task_child_visibilities_allowed,
            @Param("task_child_needs_permission") boolean task_child_needs_permission,
            @Param("task_child_complete_permission") boolean task_child_complete_permission,
            @Param("can_leader_task_foreign") boolean can_leader_task_foreign,
            @Param("task_foreign_visibilities_allowed") String task_foreign_visibilities_allowed,
            @Param("task_foreign_needs_permission") boolean task_foreign_needs_permission,
            @Param("task_foreign_complete_permission") boolean task_foreign_complete_permission
    );

}
