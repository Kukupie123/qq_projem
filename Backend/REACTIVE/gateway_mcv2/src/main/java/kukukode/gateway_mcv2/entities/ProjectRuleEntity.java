package kukukode.gateway_mcv2.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@AllArgsConstructor
@NoArgsConstructor
@Data
//@Entity WE CAN'T ADD THIS WHEN USING R2DBC
@Table("projectrules")
@ToString
public class ProjectRuleEntity {
    @Id
    int id;
    String visibility;
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
