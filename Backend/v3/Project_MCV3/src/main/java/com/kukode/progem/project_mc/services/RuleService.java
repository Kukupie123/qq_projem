package com.kukode.progem.project_mc.services;

import com.kukode.progem.project_mc.models.BaseResponse;
import com.kukode.progem.project_mc.models.entities.ProjectRuleEntity;
import com.kukode.progem.project_mc.utils.APIURLs;
import com.kukode.progem.project_mc.utils.MCHostsNPorts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RuleService {

    Logger log = LoggerFactory.getLogger("Rule Service");

    @Value(MCHostsNPorts.PROJECTRULE_PORT)
    String projectRulePort;
    @Value(MCHostsNPorts.PROJECTRULE_HOST)
    String projectRuleHost;

    @Value(APIURLs.PROJECTRULE_Base)
    String projectRuleBase;
    @Value(APIURLs.PROJECTRULE_GETSIMILAR)
    String projectRuleGetSimilarRule;


    public Mono<ResponseEntity<BaseResponse<Integer>>> getSimilarRule(ProjectRuleEntity rule) {
        String url = "http://" + projectRuleHost + ":" + projectRulePort + "/" + projectRuleBase + "/" + projectRuleGetSimilarRule;
        log.info("getSimilarRule called with url {} and rule {}", url, rule);
        WebClient client = WebClient.create(url);
        return client.post()
                .bodyValue(rule)
                .exchangeToMono(clientResponse -> {
                            var ruleIDMono = clientResponse.bodyToMono(BaseResponse.class);
                            return ruleIDMono.flatMap(
                                    integerBaseResponse -> {
                                        //Check status code and return if NOT 200 ok
                                        if (!clientResponse.statusCode().is2xxSuccessful())
                                            return Mono.just(ResponseEntity.status(clientResponse.statusCode())
                                                    .body(new BaseResponse<Integer>(-1, integerBaseResponse.getMessage())));

                                        int projectRuleID = (int) integerBaseResponse.getData();
                                        return Mono.just(ResponseEntity.ok(new BaseResponse<Integer>(projectRuleID, integerBaseResponse.getMessage())));
                                    }
                            );
                        }
                );

    }

    /**
     * Returns the rule based on id passed as argument
     *
     * @param id the id of the rule
     * @return rule entity
     */
    public Mono<ResponseEntity<BaseResponse<ProjectRuleEntity>>> getRule(int id) {
        return Mono.just(ResponseEntity.internalServerError().body(new BaseResponse<ProjectRuleEntity>(null, "Work in progress, id passed :" + id)));
    }

    /**
     * Makes sure that the childRule has same OR less privilege than parentRule.
     * It is going to modify the child rule as needed if it has to be changed to have same privilege as parentRule in case it has more privilege
     * than parent and returns it back as payload
     *
     * @param parentRule The parent rule
     * @param childRule  The Project rule
     * @return A new modified childRule with at least the same privilege as parentRule in case those attributes were previously more than that of parent rule
     */
    public Mono<ResponseEntity<BaseResponse<ProjectRuleEntity>>> validateRule(ProjectRuleEntity parentRule, ProjectRuleEntity childRule) {
        return Mono.just(ResponseEntity.internalServerError().body(new BaseResponse<ProjectRuleEntity>(null, "Work in progress, childRule passed :" + childRule + "parentRule passed : " + parentRule)));

    }
}
