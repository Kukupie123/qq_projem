package kukukode.gateway_mcv2.controller;

import kukukode.gateway_mcv2.entities.UserEntity;
import kukukode.gateway_mcv2.util.MicroServiceURLs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {


    @PostMapping("/signin")
    public Mono<Boolean> signin(@RequestBody UserEntity userEntity) {


        //Sign-in returns a boolean and status code
        WebClient client = WebClient.create(MicroServiceURLs.AUTH+MicroServiceURLs.AUTH_SIGNIN);
        Mono<Boolean> success = client
                .post()
                .body(Mono.just(userEntity), UserEntity.class)
                .header("Content-Type", "application/json")
                .retrieve().bodyToMono(Boolean.class);
        return success;



        /*
        The code above may look daunting but bear with me. Let me try to explain
        1. We are creating a client with the url
        2. We configure the client with body,header,post method
        3. Next we use exchangeToMono method to send request to the URL
        4. As the parameter of exchangeToMono we do a lambda with response object, Everything inside lambda is going to be referred as 4.1, 4.2, ...
        4.1. We then extract the BodyMono from the response and store it in tokenMono
        4.2 We then map the Data inside the Mono to a responseEntity and pass the status code of response as well as the Data of the mono as body
        4.3 If we have no body we are going to trigger the switchIfEmpty method where we pass a Mono Object with response entity with the status
            code of response and empty body
         */


    }
}
