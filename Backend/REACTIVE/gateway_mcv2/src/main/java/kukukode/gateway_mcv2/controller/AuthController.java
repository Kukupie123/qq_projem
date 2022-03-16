package kukukode.gateway_mcv2.controller;

import kukukode.gateway_mcv2.entities.UserEntity;
import kukukode.gateway_mcv2.response.BaseResponse;
import kukukode.gateway_mcv2.response.BaseResponseImp;
import kukukode.gateway_mcv2.service.microservice.auth.AuthService;
import kukukode.gateway_mcv2.service.microservice.jwt.JWTMCService;
import kukukode.gateway_mcv2.util.ApplicationAttributeNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @Value(ApplicationAttributeNames.HOSTURL_AUTH)
    String gg;

    @ResponseBody
    @GetMapping("/test")
    public String test(){
        System.out.println("test called");
        return gg;

    }

    /**
     * Return Token on successful authentication
     *
     * @return JWT token
     */
    @PostMapping("/signin")
    public Mono<ResponseEntity<BaseResponse<String>>> signin(@RequestBody UserEntity userEntity) {
        var resp = authService.signin(userEntity) //Signin
                /*
                Using flatMap was the right thing that took forever for me to figure out.
                Map is going to Make Mono out of the returned Value
                Since JWT service returns a Mono I was getting Mono<Mono<ResponseEntity<String>>>
                Switching  to flatMap totally gave me the freedom to return What I want
                 */
                .flatMap(
                        booleanResponseEntity -> {
                            System.out.println("Inside flatmap");
                            //Once we get value we enter this lambda
                            if (booleanResponseEntity.getBody().getData()) {
                                //Generate JWT as Sign in was a success
                                return JWTMCService.generate(userEntity.getEmail());
                            }
                            //Create a new mono and return since sign in failed;
                            BaseResponse<String> ggez = new BaseResponseImp<>(null, booleanResponseEntity.getBody().getMessage());
                            return Mono.just(ResponseEntity.status(booleanResponseEntity.getStatusCode()).body(ggez));
                        }
                );
        return resp;
    }


    @PostMapping("/signup")
    public Mono<ResponseEntity<BaseResponse<Boolean>>> signup(@RequestBody UserEntity user) {
        return authService.signup(user);
    }

}
