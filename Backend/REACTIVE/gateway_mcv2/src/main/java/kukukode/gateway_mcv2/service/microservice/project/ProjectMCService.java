package kukukode.gateway_mcv2.service.microservice.project;

import kukukode.gateway_mcv2.entities.ProjectEntity;
import kukukode.gateway_mcv2.service.microservice.jwt.JWTMCService;
import kukukode.gateway_mcv2.util.ApplicationAttributeNames;
import kukukode.gateway_mcv2.util.MicroServiceURLs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProjectMCService {
    @Value(ApplicationAttributeNames.HOSTURL_PROJECT)
    String host;
    @Value(ApplicationAttributeNames.PORT_PROJECT)
    int port;

    final
    JWTMCService jwtmcService;

    public ProjectMCService(JWTMCService jwtmcService) {
        this.jwtmcService = jwtmcService;
    }

    public Mono<ResponseEntity<ProjectEntity>> createProject(ProjectEntity projectEntity, String token) {
        token = token.replace("Bearer ", "").trim();

        //Extract userID from token
        return jwtmcService.extractUserIDFromToken(token)
                .flatMap(stringResponseEntity -> {
                            if (stringResponseEntity.getStatusCode().is2xxSuccessful()) {
                                String userID = stringResponseEntity.getBody().getData();
                                //Extracted Successfully
                                return WebClient.create(MicroServiceURLs.PROJECT(host, port) + MicroServiceURLs.PROJECT_CREATE_ROOT)
                                        .post()
                                        .bodyValue(projectEntity)
                                        .header("Authorization", userID)
                                        .header("Content-Type","application/json")
                                        .exchangeToMono(
                                                clientResponse -> {
                                                    return clientResponse.bodyToMono(ProjectEntity.class)
                                                            .flatMap(s -> {
                                                                        return Mono.just(ResponseEntity.status(clientResponse.statusCode()).body(s));
                                                                    }
                                                            );
                                                }
                                        );

                            } else {
                                //Failed to extract token
                                return Mono.just(ResponseEntity.status(500).body(null));
                            }
                        }
                );

    }
}
