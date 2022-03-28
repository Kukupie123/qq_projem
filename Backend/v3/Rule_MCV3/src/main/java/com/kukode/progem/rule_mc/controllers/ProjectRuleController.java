package com.kukode.progem.rule_mc.controllers;

import com.kukode.progem.rule_mc.models.BaseResponse;
import com.kukode.progem.rule_mc.models.entities.ProjectRuleEntity;
import com.kukode.progem.rule_mc.services.ProjectRuleService;
import com.kukode.progem.rule_mc.utils.APIURLs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/" + APIURLs.PROJECTRULE_Base)
public class ProjectRuleController {

    Logger log = LoggerFactory.getLogger("Project-Rule Controller");
    final ProjectRuleService projectRuleService;

    public ProjectRuleController(ProjectRuleService projectRuleService) {
        this.projectRuleService = projectRuleService;
    }

    @RequestMapping(value = "/"+APIURLs.PROJECTRULE_GETSIMILAR, method = RequestMethod.POST)
    public Mono<ResponseEntity<BaseResponse<Integer>>> getSimilarProjectRule(@RequestBody ProjectRuleEntity rule) {
        log.info("Get-Similar-ProjectRule called with body {}", rule);
        Mono<Integer> ruleIDMono = projectRuleService.getSimilarRule(rule);

        return ruleIDMono.flatMap(integer -> {
            if (integer < 0) {
                //No rule exist so we need to create one
                var ruleID = projectRuleService.createRule(rule);
                return ruleID.flatMap(integer1 -> {
                    if (integer1 < 0)
                        return Mono.just(ResponseEntity.internalServerError().body(new BaseResponse<Integer>(-1, "Something went wrong")));
                    return Mono.just(ResponseEntity.ok().body(new BaseResponse<Integer>(integer1, "successfully created a new rule and returned")));
                });
            }
            //Rule Exist so we can just return it
            return Mono.just(ResponseEntity.ok().body(new BaseResponse<Integer>(integer, "Returning existing rule")));
        });

    }
}
