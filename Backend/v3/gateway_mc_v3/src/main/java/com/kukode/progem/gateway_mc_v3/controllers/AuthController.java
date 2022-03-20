package com.kukode.progem.gateway_mc_v3.controllers;

import com.kukode.progem.gateway_mc_v3.models.BaseResponse;
import com.kukode.progem.gateway_mc_v3.models.requests.SignInUp;
import com.kukode.progem.gateway_mc_v3.services.AuthService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    final Logger log = LoggerFactory.getLogger("Auth Controller Logger");
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * @param body Email and Cred
     * @return true or false as data and 200 ok status code when successful
     */
    @PostMapping("/sign-up")
    public Mono<ResponseEntity<BaseResponse<Boolean>>> signUp(@RequestBody SignInUp body) {
        //TODO: Custom Exception viewing
        log.debug("Sign-up request triggered");
        System.out.println("Sign-up endpoint triggered");
        return authService.signUp(body);

    }

    /**
     * Communicates with Auth MC to retrieve JWT token based on payload
     *
     * @param body Email and Cred
     * @return JWT token on successful authentication
     */
    @PostMapping("/sign-in")
    public Mono<ResponseEntity<BaseResponse<String>>> signIn(@RequestBody SignInUp body) {
        log.debug("Sign-in request triggered");
        return null;
    }
}
