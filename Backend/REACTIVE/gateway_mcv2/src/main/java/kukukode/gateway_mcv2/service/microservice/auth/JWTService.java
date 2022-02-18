package kukukode.gateway_mcv2.service.microservice.auth;

import kukukode.gateway_mcv2.util.MicroServiceURLs;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class JWTService {
    //GENERATE Returns a
    public Mono<ResponseEntity<String>> generate(String id) {
        WebClient client = WebClient.create(MicroServiceURLs.JWT + MicroServiceURLs.JWT_GENERATE(id));
        return client
                .get()
                .header("Content-Type", "application/json")
                .exchangeToMono(clientResponse -> {
                    var token = clientResponse.bodyToMono(String.class);
                    return token.map(s -> {
                        return ResponseEntity.status(clientResponse.statusCode()).body(s);
                    });
                });
    }

    public Mono<ResponseEntity<String>> extractUserIDFromToken(String token) {

        WebClient client = WebClient.create(MicroServiceURLs.JWT + MicroServiceURLs.JWT_EXTRACT);
        return client
                .post()
                .header("Authorization", token)
                .exchangeToMono(clientResponse -> {
                    return clientResponse.bodyToMono(String.class)
                            .map(o -> {
                                return ResponseEntity.status(clientResponse.statusCode()).body(o);
                            });
                });
    }
}
