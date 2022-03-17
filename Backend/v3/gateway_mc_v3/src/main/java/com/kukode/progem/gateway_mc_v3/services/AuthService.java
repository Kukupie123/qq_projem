package com.kukode.progem.gateway_mc_v3.services;

import com.kukode.progem.gateway_mc_v3.models.BaseResponse;
import com.kukode.progem.gateway_mc_v3.models.requests.SignInUp;
import com.kukode.progem.gateway_mc_v3.utils.applicationproperties.MicroserviceAttributeNames;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    @Value(MicroserviceAttributeNames.AUTH_HOST)
    String authenticationHost;
    @Value(MicroserviceAttributeNames.AUTH_PORT)
    String authenticationPort;


    public Mono<ResponseEntity<BaseResponse<Boolean>>> signUp(SignInUp body) {
        //TODO: Change "/auth/sign-in" hardcore to a value that is going to be loaded from property file called authAPI.yml stored in git
        WebClient client = WebClient.create("http://" + authenticationHost + ":" + authenticationPort + "/auth/sign-up");

        try {
            return client
                    .post()
                    .bodyValue(body)
                    .exchangeToMono(resp -> {
                                //TODO: Figure out how to specify the generic type of BaseResponse.class
                                return resp.bodyToMono(BaseResponse.class)
                                        .map(authBaseResp -> {
                                                    return ResponseEntity.status(resp.statusCode()).body(authBaseResp);
                                                }
                                        );
                            }
                    );
        } catch (Exception e) {
            BaseResponse<Boolean> response = new BaseResponse<>(false, e.getMessage());
            return Mono.just(ResponseEntity.internalServerError().body(response));
        }

    }
}
