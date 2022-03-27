package com.kukode.progem.auth_mc_v3.controllers;

import com.kukode.progem.auth_mc_v3.models.BaseResponse;
import com.kukode.progem.auth_mc_v3.models.requests.SignInUp;
import com.kukode.progem.auth_mc_v3.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Main Controller is going to handle the basic requests such as signin, signup
 */
@RestController
@RequestMapping("/"+"${auth.base}")
public class AuthController {

    final AuthService authService;
    final Logger log = LoggerFactory.getLogger("MainController");

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Verifies the credentials and returns a JWT token
     */
    @PostMapping("/"+"${auth.signin}")
    public Mono<ResponseEntity<BaseResponse<String>>> signIn(@RequestBody SignInUp body) {
        //TODO: Add exceptions handling for expiration and other stuff
        log.info("Sign-In Triggered with values {}", body.toString());
        return authService.signIn(body.getEmail(), body.getPassword())
                .flatMap(s -> Mono.just(ResponseEntity.ok(new BaseResponse<String>(s, "generated token"))))
                .onErrorResume(throwable -> {
                    //When we get error we are going to return a response entity with the following message
                    return Mono.just(ResponseEntity.internalServerError().body(new BaseResponse<String>(null, throwable.getMessage())));
                });
    }

    @PostMapping("/"+"${auth.signup}")
    public Mono<ResponseEntity<BaseResponse<Boolean>>> signUp(@RequestBody SignInUp body) {
        log.info(("Sign-up Triggered with values " + body.toString()));
        return authService.signUp(body.getEmail(), body.getPassword()).flatMap(resp ->
                {
                    if (resp.getData()) return Mono.just(ResponseEntity.ok(resp));
                    return Mono.just(ResponseEntity.internalServerError().body(resp));
                }
        );
    }
}
