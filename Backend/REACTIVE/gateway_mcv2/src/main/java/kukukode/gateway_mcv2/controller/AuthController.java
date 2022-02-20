package kukukode.gateway_mcv2.controller;

import kukukode.gateway_mcv2.entities.UserEntity;
import kukukode.gateway_mcv2.service.microservice.auth.AuthService;
import kukukode.gateway_mcv2.service.microservice.jwt.JWTMCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {
    final private AuthService authService;
    final private JWTMCService JWTMCService;

    @Autowired
    public AuthController(AuthService authService, JWTMCService JWTMCService) {
        this.authService = authService;
        this.JWTMCService = JWTMCService;
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
                /*
                Using flatMap was the right thing that took forever for me to figure out.
                Map is going to Make Mono out of the returned Value
                Since JWT service returns a Mono I was getting Mono<Mono<ResponseEntity<String>>>
                Switching to flatMap totally gave me the freedom to return What I want
                 */
                .flatMap(booleanResponseEntity -> {
                    //Once we get value we enter this lambda
                    if (booleanResponseEntity.getBody()) {
                        //Generate JWT as Sign in was a success
                        return JWTMCService.generate(userEntity.getEmail());
                    }
                    //Create a new mono and return since sign in failed;
                    return Mono.just(ResponseEntity.status(booleanResponseEntity.getStatusCode()).body(""));
                });
        return resp;

    }

}
