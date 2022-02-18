package kukukode.gateway_mcv2.service.microservice.auth;

import kukukode.gateway_mcv2.entities.UserEntity;
import kukukode.gateway_mcv2.util.ApplicationAttributeNames;
import kukukode.gateway_mcv2.util.MicroServiceURLs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthService {
    @Value(ApplicationAttributeNames.HOSTURL_AUTH)
    String hostUrl;
    @Value(ApplicationAttributeNames.PORT_AUTH)
    int port;

    public Mono<ResponseEntity<Boolean>> signin(UserEntity userEntity) {
        WebClient client = WebClient.create(MicroServiceURLs.AUTH(hostUrl, port) + MicroServiceURLs.AUTH_SIGNIN);
        return client
                .post()
                .body(Mono.just(userEntity), UserEntity.class)
                .header("Content-Type", "application/json")
                .exchangeToMono(clientResponse -> {
                    var success = clientResponse.bodyToMono(Boolean.class);
                    return success.map(aBoolean -> {
                        return ResponseEntity.status(clientResponse.statusCode()).body(aBoolean);
                    });

                });
    }
}
