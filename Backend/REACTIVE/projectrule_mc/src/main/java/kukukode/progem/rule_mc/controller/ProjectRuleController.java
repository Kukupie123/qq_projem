package kukukode.progem.rule_mc.controller;

import kukukode.progem.rule_mc.entity.ProjectRuleEntity;
import kukukode.progem.rule_mc.service.ProjectRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/projectrule")
public class ProjectRuleController {
    final ProjectRuleService projectRuleService;

    public ProjectRuleController(ProjectRuleService projectRuleService) {
        this.projectRuleService = projectRuleService;
    }


    /**
     * Takes in a rule and creates it if it doesnt exist
     *
     * @return returns id of the rule
     */

    @PutMapping("/")
    public Mono<ResponseEntity<Integer>> updateRuleIfDoesntExist(@RequestBody ProjectRuleEntity rule) {
        Mono<Integer> ruleIDMono = projectRuleService.getSimilarRule(rule);

        return ruleIDMono.flatMap(integer -> {
            if (integer < 0) {
                //No rule exist so we need to create one
                var ruleID = projectRuleService.createRule(rule);
                return ruleID.flatMap(integer1 -> {
                    if (integer1 < 0) return Mono.just(ResponseEntity.internalServerError().body(-1));
                    return Mono.just(ResponseEntity.ok().body(integer1));
                });
            }
            //Rule Exist so we can just return it
            System.out.println("RETURNING");
            return Mono.just(ResponseEntity.ok().body(integer));
        });
    }
}
