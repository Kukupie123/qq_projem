package com.kukode.progem.gateway_mc_v3.services;

import com.kukode.progem.gateway_mc_v3.models.BaseResponse;
import com.kukode.progem.gateway_mc_v3.models.entities.Project;
import com.kukode.progem.gateway_mc_v3.models.requests.CreateProject;
import com.kukode.progem.gateway_mc_v3.utils.applicationproperties.MCHostsNPorts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProjectService {
    final Logger log = LoggerFactory.getLogger("Project Service");

    @Value(MCHostsNPorts.PROJECT_HOST)
    String projectHost;
    @Value(MCHostsNPorts.PROJECT_PORT)
    String projectPort;

    @Value("${project.base}")
    String projectBase;

    /**
     * @param project The payload
     * @return Project Entity after it has created the project
     */
    public Mono<ResponseEntity<BaseResponse<Project>>> createProject(CreateProject project) {
        String url = "http://" + projectHost + ":" + projectHost + "/" + projectBase;
        log.info("CreateProject called with payload {} and url {}", project, url);
        WebClient client = WebClient.create(url);
        return client.post()
                .bodyValue(project)
                .exchangeToMono(clientResponse -> {
                    return clientResponse.bodyToMono(BaseResponse.class)
                            .flatMap(
                                    baseResponse -> Mono.just(ResponseEntity.status(clientResponse.statusCode()).body(baseResponse))
                            );
                });
    }


}
