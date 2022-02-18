package kukukode.jwt_mc.controller;

import kukukode.jwt_mc.service.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/jwt")
public class MainController {

    final private JWTService jwtService;
    final Logger log;

    @Autowired
    public MainController(JWTService jwtService) {
        this.log = LoggerFactory.getLogger("GATE LOG");

        this.jwtService = jwtService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generate(@RequestParam String id) {
        try {
            String token = jwtService.generateToken(id);
            return Mono.just(ResponseEntity.ok().body(token));
        } catch (Exception e) {
            return Mono.just(ResponseEntity.internalServerError().body(e.getMessage()));
        }
    }

    @PostMapping("/getuserid")
    public Mono<ResponseEntity<String>> getUserID(@RequestHeader(name = "Authorization") String token) {
        log.debug("Get user ID Called from JWT MC");
        try {
            token = token.replace("Bearer ", "");
            System.out.println(token);
            var userID = jwtService.extractUserName(token);
            return Mono.just(ResponseEntity.ok().body(userID));
        } catch (Exception e) {
            return Mono.just(ResponseEntity.internalServerError().body(e.getMessage()));
        }
    }

}
