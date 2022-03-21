package com.kukode.progem.gateway_mc_v3.controllers;

import com.kukode.progem.gateway_mc_v3.models.BaseResponse;
import com.kukode.progem.gateway_mc_v3.models.requests.SignInUp;
import com.kukode.progem.gateway_mc_v3.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    //**Declarations**
    final Logger log = LoggerFactory.getLogger("Auth Controller");
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //**Methods**

    /**
     * @param body Email and Cred
     * @return true or false as data, message as the reason behind it IF data is false
     */
    @PostMapping("/sign-up")
    public Mono<ResponseEntity<BaseResponse<Boolean>>> signUp(@RequestBody SignInUp body) {
        //TODO: Custom Exception viewing
        log.info("Sign-up request triggered with request "+body.toString());
        return authService.signUp(body);

    }

    /**
     * Communicates with Auth MC to retrieve JWT token based on payload
     * @param body Email and Cred
     * @return JWT token on successful authentication
     */
    @PostMapping("/sign-in")
    public Mono<ResponseEntity<BaseResponse<String>>> signIn(@RequestBody SignInUp body) {
        log.info("Sign-in request triggered");
        return null;
    }
}
