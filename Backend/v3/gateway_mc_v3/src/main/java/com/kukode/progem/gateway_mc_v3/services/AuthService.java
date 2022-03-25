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

    @Value("${auth.base}")
    String authBase;
    @Value("${auth.signup}")
    String authSignUp;
    @Value("${auth.signin}")
    String authSignIn;

    @Value("${jwt.base}")
    String jwtBase;
    @Value("${jwt.getuserid}")
    String jwtGetUserID;

    public Mono<ResponseEntity<BaseResponse<Boolean>>> signUp(SignInUp body) {
        String url = "http://" + authenticationHost + ":" + authenticationPort + "/" + authBase + authSignUp;
        log.info("SignUp on Auth MC with URL {} and body  {}", url, body.toString());
        WebClient client = createClient(url);
        return client
                .post()
                .bodyValue(body)
                .exchangeToMono(resp -> {
                            //TODO: Figure out how to specify the generic type of BaseResponse.class
                            return resp.bodyToMono(BaseResponse.class)
                                    .map(authBaseResp -> {
                                                log.info("Response : " + authBaseResp.toString() + ", Status : {}", resp.statusCode());
                                                return ResponseEntity.status(resp.statusCode()).body(authBaseResp);
                                            }
                                    );
                        }
                );
    }

    public Mono<ResponseEntity<BaseResponse<String>>> signIn(SignInUp body) {
        String url = "http://" + authenticationHost + ":" + authenticationPort + "/" + authBase + authSignIn;
        log.info("SignIn on AuthMC with URL {} and body {}", url, body.toString());
        WebClient client = createClient(url);
        return client.post()
                .bodyValue(body)
                .exchangeToMono(clientResponse -> {
                            return clientResponse.bodyToMono(BaseResponse.class)
                                    .map(baseResponse -> {
                                                log.info("Response : " + baseResponse.toString() + ", Status : {}", clientResponse.statusCode());
                                                return ResponseEntity.status(clientResponse.statusCode()).body(baseResponse);
                                            }
                                    );
                        }
                );

    }

    public Mono<ResponseEntity<BaseResponse<String>>> getUserIDFromJWTToken(String token) {
        String url = "http://" + authenticationHost + ":" + authenticationPort + "/" + jwtBase + jwtGetUserID;
        log.info("GetUserIDFromJWTToken on AuthMC with URL {} and token {}", url, token);

        WebClient client = createClient(url);
        return client.post()
                .header("Authorization", "Bearer " + token)
                .exchangeToMono(clientResponse -> {
                            return clientResponse.bodyToMono(BaseResponse.class)
                                    .map(
                                            baseResponse -> {
                                                log.info("Response : " + baseResponse.toString() + ", Status : {}", clientResponse.statusCode());
                                                return ResponseEntity.status(clientResponse.statusCode()).body(baseResponse);
                                            }
                                    );
                        }
                );
    }

    private WebClient createClient(String url) {
        return WebClient.create(url);
    }
}
