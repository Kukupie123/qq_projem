package com.kukode.progem.auth_mc_v3.services;

import com.kukode.progem.auth_mc_v3.models.BaseResponse;
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

    /**
     * Returns true or false with a message IF false stating the cause behind the false value
     *
     * @param email
     * @param password
     * @return
     */
    public Mono<BaseResponse<Boolean>> signUp(String email, String password) {

        //Check if user already exist
        return userRepo.findById(email)
                .defaultIfEmpty(new User(false, null, null))
                .flatMap(user -> {

                            if (user.getEmail() == null && user.getCred() == null) {
                                //This object is from the "defaultIfEmpty" function
                                //this implies that there is no previous object so we can execute save function
                                return userRepo
                                        .save(new User(true, email, password))
                                        .flatMap(user1 -> {
                                            BaseResponse<Boolean> resp = new BaseResponse<>(true, "");
                                            return Mono.just(resp);
                                        });
                            }
                            //Found a user in the database already
                            return Mono.just(new BaseResponse<Boolean>(false, "User already exist"));
                        }
                );


    }

    public Mono<String> signIn(String email, String password) {

        return userRepo.signIn(email, password)
                .flatMap(user -> {
                    System.out.println(user.toString() + " SIGN IN QUERY FOUND A RESULT");
                    return Mono.just(jwtService.generateToken(email));
                }).switchIfEmpty(Mono.error(new Exception("Wrong credentials")));
    }

}
