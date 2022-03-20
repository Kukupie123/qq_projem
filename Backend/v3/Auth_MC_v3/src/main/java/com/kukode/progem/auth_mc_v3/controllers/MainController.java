package com.kukode.progem.auth_mc_v3.controllers;

import com.kukode.progem.auth_mc_v3.models.BaseResponse;
import com.kukode.progem.auth_mc_v3.models.requests.SignInUp;
import com.kukode.progem.auth_mc_v3.services.AuthService;
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
//TODO: Make these variables non-hardcoded
@RequestMapping("/api/v1/auth")
public class MainController {

    final AuthService authService;

    public MainController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Verifies the credentials and returns a JWT token
     */
    @PostMapping("/sign-in")
    public Mono<ResponseEntity<BaseResponse<String>>> signIn(@RequestBody SignInUp body) {
        return null;
    }

    @PostMapping("/sign-up")
    public Mono<ResponseEntity<BaseResponse<Boolean>>> signUp(@RequestBody SignInUp body) {
        return authService.signUp(body.getEmail(), body.getPassword()).flatMap(aBoolean -> {
                    BaseResponse<Boolean> response = new BaseResponse<>(false, "message");

                    if (aBoolean) {
                        response.setData(true);
                        response.setMessage("Successful");
                        return Mono.just(
                                ResponseEntity.ok().body(response)
                        );
                    } else {
                        response.setData(false);
                        response.setMessage("Something went wrong");
                        return Mono.just(
                                ResponseEntity.internalServerError().body(response)
                        );
                    }

                }
        );
    }
}
