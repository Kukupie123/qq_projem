package com.kukode.progem.project_mc.models.requests;

import com.kukode.progem.project_mc.models.entities.ProjectRuleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ValidateRule {
    ProjectRuleEntity parentRule;
    ProjectRuleEntity childRule;
}
