package kukukode.gateway_mcv2.service.microservice.rules.projectrule;

import kukukode.gateway_mcv2.entities.ProjectRuleEntity;
import kukukode.gateway_mcv2.util.ApplicationAttributeNames;
import kukukode.gateway_mcv2.util.MicroServiceURLs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProjectRuleMCService {
    @Value(ApplicationAttributeNames.HOSTURL_PROJECTRULE)
    String host;
    @Value(ApplicationAttributeNames.PORT_PROJECTRULE)
    int port;

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

    public Mono<ResponseEntity<Integer>> createRuleIfNotExist(ProjectRuleEntity rule) {
        WebClient client = WebClient.create(MicroServiceURLs.RULE(host, port) + MicroServiceURLs.RULE_GETRULE);
        return client
                .put()
                .bodyValue(rule)
                .exchangeToMono(
                        clientResponse -> {
                            return clientResponse.bodyToMono(Integer.class)
                                    .flatMap(in -> {
                                                return Mono.just(ResponseEntity.status(clientResponse.statusCode()).body(in));
                                            }
                                    );
                        }
                );
    }

}
