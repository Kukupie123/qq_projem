package com.kukode.progem.gateway_mc_v3.controllers;

import com.kukode.progem.gateway_mc_v3.models.BaseResponse;
import com.kukode.progem.gateway_mc_v3.models.requests.SignInUp;
import com.kukode.progem.gateway_mc_v3.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * @param body email and cred
     * @return true or false as data and 200 ok status code when successful
     */
    @PostMapping("/sign-up")
    public Mono<ResponseEntity<BaseResponse<Boolean>>> signUp(@RequestBody SignInUp body) {
        //TODO: Custom Exception viewing
        System.out.println("Sign-up endpoint triggered");
        return authService.signUp(body);

    }
}
