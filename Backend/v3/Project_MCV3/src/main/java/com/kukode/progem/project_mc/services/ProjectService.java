package com.kukode.progem.project_mc.services;

import com.kukode.progem.project_mc.models.entities.Project;
import com.kukode.progem.project_mc.models.entities.ProjectRuleEntity;
import com.kukode.progem.project_mc.repo.ProjectRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProjectService {
    final ProjectRepo projectRepo;

    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    /**
     * Creates a ruleEntity with root privilege
     */
    public ProjectRuleEntity createRootProjectRule(String visibility) {
        if (visibility.trim().equalsIgnoreCase("private") == false) {
            System.out.println("Visibility is invalid " + visibility);
            return null;
        }
        var rule = new ProjectRuleEntity();
        rule.setVisibility(visibility);
        rule.setChild_visibilities_allowed("*");
        rule.setHave_children(true);
        rule.setHave_children_needs_permission(false);
        rule.setCan_leader_task_member(true);
        rule.setTask_member_visibilities_allowed("*");
        rule.setTask_member_needs_permission(false);
        rule.setTask_member_complete_permission(false);
        rule.setCan_leader_task_children(true);
        rule.setTask_child_visibilities_allowed("*");
        rule.setTask_child_needs_permission(false);
        rule.setTask_child_complete_permission(false);
        rule.setCan_leader_task_foreign(true);
        rule.setTask_foreign_visibilities_allowed("*");
        rule.setTask_foreign_needs_permission(false);
        rule.setTask_foreign_complete_permission(false);
        rule.setId(-69); // dummy ID. Will Never be used
        return rule;
    }

    public Mono<Project> createProject(Project project) {
        return projectRepo.save(project);

    }
}
