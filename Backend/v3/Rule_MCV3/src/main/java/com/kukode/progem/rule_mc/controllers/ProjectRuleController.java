package com.kukode.progem.rule_mc.controllers;

import com.kukode.progem.rule_mc.models.BaseResponse;
import com.kukode.progem.rule_mc.models.entities.ProjectRuleEntity;
import com.kukode.progem.rule_mc.models.requests.ValidateRule;
import com.kukode.progem.rule_mc.services.ProjectRuleService;
import com.kukode.progem.rule_mc.utils.APIURLs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/" + APIURLs.PROJECTRULE_Base)
public class ProjectRuleController {

    Logger log = LoggerFactory.getLogger("Project-Rule Controller");
    final ProjectRuleService projectRuleService;

    public ProjectRuleController(ProjectRuleService projectRuleService) {
        this.projectRuleService = projectRuleService;
    }

    @RequestMapping(value = "/" + APIURLs.PROJECTRULE_GETSIMILAR, method = RequestMethod.POST)
    public Mono<ResponseEntity<BaseResponse<Integer>>> getSimilarProjectRule(@RequestBody ProjectRuleEntity rule) {
        log.info("Get-Similar-ProjectRule called with body {}", rule);
        Mono<Integer> ruleIDMono = projectRuleService.getSimilarRule(rule);

        return ruleIDMono.flatMap(integer -> {
            if (integer < 0) {
                //No rule exist so we need to create one
                log.info("No existing rule found with similar preference, creating a new rule");
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


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Mono<ResponseEntity<BaseResponse<ProjectRuleEntity>>> getRule(@PathVariable String id) {
        return projectRuleService.getRule(Integer.parseInt(id))
                .flatMap(projectRuleEntity -> {
                    if (projectRuleEntity.getId() < 0) {
                        return Mono.just(ResponseEntity.status(404).body(new BaseResponse<>(null, "Rule not found with ID " + id)));
                    }
                    return Mono.just(ResponseEntity.ok(new BaseResponse<ProjectRuleEntity>(projectRuleEntity, "Found project")));
                });
    }

    /**
     * Takes ParentRule and child rule as payload. Makes sure that the child rule is at max having the same privilege as it's parent
     * A child is not allowed to have more privilege than it's parent
     */
    @RequestMapping(value = "/" + APIURLs.PROJECTRULE_VALIDATERULE, method = RequestMethod.POST)
    public Mono<ResponseEntity<BaseResponse<ProjectRuleEntity>>> validateRule(@RequestBody ValidateRule ruleReq) {
        //Validate if parentProjectRule is Valid
        if (!ruleReq.getParentRule().isValid(false))
            return Mono.just(ResponseEntity.internalServerError().body(new BaseResponse<ProjectRuleEntity>(null, "parent rule is invalid")));

        //validate if childProjectRule is valid
        if (!ruleReq.getChildRule().isValid(true))
            //we do not have an ID for childProject yet as it may or may not exist
            return Mono.just(ResponseEntity.internalServerError().body(new BaseResponse<ProjectRuleEntity>(null, "ChildProject rule is invalid")));

        //Both rule are valid now we can compare
        try {
            var validatedChildRule = projectRuleService.validateRule(ruleReq.getParentRule(), ruleReq.getChildRule());
            return Mono.just(ResponseEntity.ok(new BaseResponse<ProjectRuleEntity>(validatedChildRule, "")));
        } catch (Exception e) {
            return Mono.just(ResponseEntity.internalServerError().body(new BaseResponse<ProjectRuleEntity>(null, e.getMessage())));
        }


    }
}
