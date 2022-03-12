package kukukode.progem.projectleadermc.services.mc_services;

import kukukode.progem.projectleadermc.entity.ProjectEntity;
import kukukode.progem.projectleadermc.util.ApplicationAttributeNames;
import kukukode.progem.projectleadermc.util.MicroserviceURLs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProjectMCServices {
    @Value(ApplicationAttributeNames.HOSTURL_PROJECT)
    String host;
    @Value(ApplicationAttributeNames.PORT_PROJECT)
    int port;

    public Mono<ProjectEntity> getProject(int projectID) {
        return WebClient.create(MicroserviceURLs.PROJECT(host, port))
                .get()
                .uri(Integer.toString(projectID))

    }

}
