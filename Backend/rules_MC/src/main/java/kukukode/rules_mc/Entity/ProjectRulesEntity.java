package kukukode.rules_mc.Entity;

import javax.persistence.*;

@Entity
@Table(name = "projectrules")
public class ProjectRulesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String visibility;
    String child_visibilities_allowed;
    boolean have_children;
    boolean have_children_needs_permission;
    boolean can_leader_task_member;
    String task_member_visibilities_allowed;
    boolean task_member_needs_permission;
    boolean task_member_complete_permission;
    boolean can_leader_task_children;
    String task_child_visibilities_allowed;
    boolean task_child_needs_permission;
    boolean task_child_complete_permission;
    boolean can_leader_task_foreign;
    String task_foreign_visibilities_allowed;
    boolean task_foreign_needs_permission;
    boolean task_foreign_complete_permission;
}
