package kukukode.progem.apigateway.config;


import kukukode.progem.apigateway.util.RoutePathes;
import kukukode.progem.apigateway.util.RouteURIs;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path(RoutePathes.AUTH).uri(RouteURIs.AUTH))
                .build();
    }
}
