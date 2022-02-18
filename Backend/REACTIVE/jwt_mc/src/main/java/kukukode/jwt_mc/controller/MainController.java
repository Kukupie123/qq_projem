package kukukode.jwt_mc.controller;

import kukukode.jwt_mc.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/jwt")
public class MainController {

    final private JWTService jwtService;

    @Autowired
    public MainController(JWTService jwtService) {
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

}
