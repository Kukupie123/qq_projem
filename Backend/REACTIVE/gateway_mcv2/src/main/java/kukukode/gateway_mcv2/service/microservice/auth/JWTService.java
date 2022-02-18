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
}
