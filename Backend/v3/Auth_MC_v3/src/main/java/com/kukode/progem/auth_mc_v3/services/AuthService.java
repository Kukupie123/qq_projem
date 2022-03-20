package com.kukode.progem.auth_mc_v3.services;

import com.kukode.progem.auth_mc_v3.models.entities.User;
import com.kukode.progem.auth_mc_v3.repo.UserRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthService {
    final UserRepo userRepo;
    final JWTService jwtService;

    public AuthService(UserRepo userRepo, JWTService jwtService) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }

    public Mono<Boolean> signUp(String email, String password) {

        //Check if user already exist

        return userRepo.save(new User(true, email, password))
                .flatMap(user -> {
                    if (user != null) return Mono.just(true);
                    return Mono.just(false);
                })
                .onErrorReturn(null)
                .defaultIfEmpty(false);

    }

}
