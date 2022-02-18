package kukukode.gateway_mcv2.controller;

import kukukode.gateway_mcv2.entities.UserEntity;
import kukukode.gateway_mcv2.service.microservice.auth.AuthService;
import kukukode.gateway_mcv2.service.microservice.auth.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    final private AuthService authService;
    final private JWTService jwtService;

    @Autowired
    public AuthController(AuthService authService, JWTService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    /**
     * Return Token on successful authentication
     *
     * @return JWT token
     */
    @PostMapping("/signin")
    public Mono<ResponseEntity<String>> signin(@RequestBody UserEntity userEntity) {

        //Sign-in returns a ResponseEntity<Boolean>
        Mono<ResponseEntity<String>> resp = authService.signin(userEntity) //Signin
                .flatMap(booleanResponseEntity -> {
                    //Once we get value we enter this lambda
                    if (booleanResponseEntity.getBody()) {
                        //Generate JWT as Sign in was a success
                        return jwtService.generate(userEntity.getEmail());
                    }
                    //Create a new mono and return since sign in failed;
                    return Mono.just(ResponseEntity.status(booleanResponseEntity.getStatusCode()).body(""));
                });
        return resp;

    }
}
