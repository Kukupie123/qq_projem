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
        return userRepo.findById(email)
                .defaultIfEmpty(new User(false, null, null))
                .flatMap(user -> {

                            if (user.getEmail() == null && user.getCred() == null) {
                                //This object is from the "defaultIfEmpty" function
                                //this implies that there is no previous object so we can execute save function
                                return userRepo
                                        .save(new User(true, email, password))
                                        .flatMap(user1 -> Mono.just(true));
                            }
                            //Found a user in the database already
                            return Mono.error(new Exception("User already exists"));
                        }
                );

    }


}
