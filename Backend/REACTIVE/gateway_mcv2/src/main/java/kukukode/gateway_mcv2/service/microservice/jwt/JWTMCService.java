package kukukode.gateway_mcv2.service.microservice.jwt;

import kukukode.gateway_mcv2.response.BaseResponse;
import kukukode.gateway_mcv2.response.BaseResponseImp;
import kukukode.gateway_mcv2.util.ApplicationAttributeNames;
import kukukode.gateway_mcv2.util.MicroServiceURLs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class JWTMCService {
    @Value(ApplicationAttributeNames.HOSTURL_JWT)
    String hostUrl;
    @Value(ApplicationAttributeNames.PORT_JWT)
    int port;

    //GENERATE Returns a
    public Mono<ResponseEntity<BaseResponse<String>>> generate(String id) {
        WebClient client = WebClient.create(MicroServiceURLs.JWT(hostUrl, port) + MicroServiceURLs.JWT_GENERATE(id));
        return client
                .get()
                .header("Content-Type", "application/json")
                .exchangeToMono(clientResponse -> {
                    var token = clientResponse.bodyToMono(BaseResponseImp.class);
                    return token.map(s -> {
                        return ResponseEntity.status(clientResponse.statusCode()).body(s);
                    });
                });
    }

    public Mono<ResponseEntity<BaseResponse<String>>> extractUserIDFromToken(String token) {
        System.out.println(MicroServiceURLs.JWT(hostUrl, port) + MicroServiceURLs.JWT_EXTRACT);
        WebClient client = WebClient.create(MicroServiceURLs.JWT(hostUrl, port) + MicroServiceURLs.JWT_EXTRACT);
        return client
                .post()
                .header("Authorization", token)
                .exchangeToMono(clientResponse -> {
                    return clientResponse.bodyToMono(BaseResponseImp.class)
                            .map(o -> {
                                return ResponseEntity.status(clientResponse.statusCode()).body(o);
                            });
                });
    }
}
