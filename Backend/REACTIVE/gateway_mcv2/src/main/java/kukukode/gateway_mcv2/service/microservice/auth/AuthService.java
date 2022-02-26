package kukukode.gateway_mcv2.service.microservice.auth;

import kukukode.gateway_mcv2.entities.UserEntity;
import kukukode.gateway_mcv2.response.BaseResponse;
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

    public Mono<ResponseEntity<BaseResponse<Boolean>>> signin(UserEntity userEntity) {
        WebClient client = WebClient.create(MicroServiceURLs.AUTH(hostUrl, port) + MicroServiceURLs.AUTH_SIGNIN);
        return client
                .post()
                .body(Mono.just(userEntity), UserEntity.class)
                .header("Content-Type", "application/json")
                .header("Content-Type", "application/json")
                .exchangeToMono(clientResponse -> {
                    var success = clientResponse.bodyToMono(BaseResponse.class);
                    return success.map(g -> {
                                return ResponseEntity.status(clientResponse.statusCode()).body(g);
                            }
                    );

                });
    }

    public Mono<ResponseEntity<BaseResponse<Boolean>>> signup(UserEntity user) {
        //Create a web client

        //localhost:2000/auth/signup
        WebClient client = WebClient.create(MicroServiceURLs.AUTH(hostUrl, port) + MicroServiceURLs.AUTH_SIGNUP);
        return client
                .post()
                .bodyValue(user)
                .exchangeToMono(
                        clientResponse -> {
                            var messageMono = clientResponse.bodyToMono(BaseResponse.class);
                            return messageMono
                                    .map(s -> {
                                                return ResponseEntity.status(clientResponse.statusCode()).body(s);
                                            }
                                    );
                        }
                );
    }
}
