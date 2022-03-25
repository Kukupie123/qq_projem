package com.kukode.progem.auth_mc_v3.controllers;

import com.kukode.progem.auth_mc_v3.models.BaseResponse;
import com.kukode.progem.auth_mc_v3.services.JWTService;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("${jwt.base}")
public class JWTController {

    final JWTService jwtService;
    final Logger log = LoggerFactory.getLogger("JWT Controller");

    public JWTController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @RequestMapping(value = "${jwt.getuserid}",method = RequestMethod.POST)
    public Mono<ResponseEntity<BaseResponse<String>>> getUserID(@RequestHeader("Authorization") String token) {
        log.info("Get UserID called with token {}", token);


        try {
            String userID = jwtService.extractUserName(token.replace("Bearer ", ""));
            if (!userID.isEmpty())
                return Mono.just(ResponseEntity.ok(new BaseResponse<String>(userID, "successful")));

        } catch (ExpiredJwtException e) {
            return Mono.just(ResponseEntity.unprocessableEntity().body(new BaseResponse<String>(null, e.getMessage())));

        }
        return Mono.just(ResponseEntity.internalServerError().build());

    }
}
