package com.kukode.progem.gateway_mc_v3.services;

import com.kukode.progem.gateway_mc_v3.models.BaseResponse;
import com.kukode.progem.gateway_mc_v3.models.requests.SignInUp;
import com.kukode.progem.gateway_mc_v3.utils.applicationproperties.MicroserviceAttributeNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    final Logger log = LoggerFactory.getLogger("Auth Service");

    @Value(MicroserviceAttributeNames.AUTH_HOST)
    String authenticationHost;
    @Value(MicroserviceAttributeNames.AUTH_PORT)
    String authenticationPort;


    public Mono<ResponseEntity<BaseResponse<Boolean>>> signUp(SignInUp body) {
        String url = "http://" + authenticationHost + ":" + authenticationPort + "/api/v1/auth/sign-up";
        log.info("SignUp on Auth MC with URL {} and body  {}", url, body.toString());
        //TODO: Change "/auth/sign-in" hardcore to a value that is going to be loaded from property file called authAPI.yml stored in git
        WebClient client = WebClient.create(url);
        return client
                .post()
                .bodyValue(body)
                .exchangeToMono(resp -> {
                            //TODO: Figure out how to specify the generic type of BaseResponse.class
                            return resp.bodyToMono(BaseResponse.class)
                                    .map(authBaseResp -> {
                                                log.info("Response : " + authBaseResp.toString() + ", Status : {}", resp.statusCode().toString());
                                                return ResponseEntity.status(resp.statusCode()).body(authBaseResp);
                                            }
                                    );
                        }
                );


    }
}
