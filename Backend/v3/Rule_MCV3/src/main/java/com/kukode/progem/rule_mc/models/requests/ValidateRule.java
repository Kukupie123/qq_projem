package com.kukode.progem.rule_mc.models.requests;

import com.kukode.progem.rule_mc.models.entities.ProjectRuleEntity;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ValidateRule {
    ProjectRuleEntity parentRule;
    ProjectRuleEntity childRule;
}
