package kukukode.gateway_mcv2.controller;


import kukukode.gateway_mcv2.service.microservice.jwt.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/jwt")
public class JWTController {
    final
    JWTService jwtService;
    final Logger log;

    public JWTController(JWTService jwtService) {
        this.log = LoggerFactory.getLogger("GATE LOG");
        this.jwtService = jwtService;
    }

    @PostMapping("/getuserid")
    public Mono<ResponseEntity<String>> extractUserIDFromToken(@RequestHeader(name = "Authorization") String token) {
        log.debug("Get UserID Called From GATE MC");
        return jwtService.extractUserIDFromToken(token)
                .map(stringResponseEntity -> {
                    return stringResponseEntity;
                });
    }
}
