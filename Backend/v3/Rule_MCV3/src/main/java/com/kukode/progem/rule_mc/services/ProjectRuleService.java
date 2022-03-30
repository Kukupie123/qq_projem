package com.kukode.progem.rule_mc.services;

import com.kukode.progem.rule_mc.models.entities.ProjectRuleEntity;
import com.kukode.progem.rule_mc.repo.ProjectRuleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProjectRuleService {
    Logger log = LoggerFactory.getLogger("ProjectRule Service");
    final ProjectRuleRepo projectRuleRepo;

    public ProjectRuleService(ProjectRuleRepo projectRuleRepo) {
        this.projectRuleRepo = projectRuleRepo;
    }

    public Mono<Integer> getSimilarRule(ProjectRuleEntity rule) {
        log.debug("getSimilarRule RULE SERVICE with rule {}", rule);

        Mono<ProjectRuleEntity> resultRule = projectRuleRepo.findSimilarRule(
                rule.getVisibility(),
                rule.getChild_visibilities_allowed(),
                rule.isHave_children(),
                rule.isHave_children_needs_permission(),
                rule.isCan_leader_task_member(),
                rule.getTask_member_visibilities_allowed(),
                rule.isTask_member_needs_permission(),
                rule.isTask_member_complete_permission(),
                rule.isCan_leader_task_children(),
                rule.getTask_child_visibilities_allowed(),
                rule.isTask_child_needs_permission(),
                rule.isTask_child_complete_permission(),
                rule.isCan_leader_task_foreign(),
                rule.getTask_foreign_visibilities_allowed(),
                rule.isTask_foreign_needs_permission(),
                rule.isTask_foreign_complete_permission()
        );

        //Converts the mono to int using flatmap and returns it else -1
        return resultRule.flatMap(projectRuleEntity -> {
            log.info("RULE FOUND ID " + projectRuleEntity.getId());
            return Mono.just(projectRuleEntity.getId());
        }).defaultIfEmpty(-1);
    }

    public Mono<Integer> createRule(ProjectRuleEntity rule) {
        ProjectRuleEntity newRule = new ProjectRuleEntity();

        log.info("Creating new rule {}", rule);

        newRule.setVisibility(rule.getVisibility());
        newRule.setChild_visibilities_allowed(rule.getChild_visibilities_allowed());
        newRule.setHave_children(rule.isHave_children());
        newRule.setHave_children_needs_permission(rule.isHave_children_needs_permission());
        newRule.setCan_leader_task_member(rule.isCan_leader_task_member());
        newRule.setTask_member_visibilities_allowed(rule.getTask_member_visibilities_allowed());
        newRule.setTask_member_needs_permission(rule.isTask_member_needs_permission());
        newRule.setTask_member_complete_permission(rule.isTask_member_complete_permission());
        newRule.setCan_leader_task_children(rule.isCan_leader_task_children());
        newRule.setTask_child_visibilities_allowed(rule.getTask_child_visibilities_allowed());
        newRule.setTask_child_needs_permission(rule.isTask_child_needs_permission());
        newRule.setTask_child_complete_permission(rule.isTask_child_complete_permission());
        newRule.setCan_leader_task_foreign(rule.isCan_leader_task_foreign());
        newRule.setTask_foreign_visibilities_allowed(rule.getTask_foreign_visibilities_allowed());
        newRule.setTask_foreign_needs_permission(rule.isTask_foreign_needs_permission());
        newRule.setTask_foreign_complete_permission(rule.isTask_foreign_complete_permission());
        var a = projectRuleRepo.save(newRule);

        //Map = Holds the old mono
        //FlatMap = You have to create your own mono
        return a.map(projectRuleEntity -> projectRuleEntity.getId())
                .defaultIfEmpty(-1);
    }
}
