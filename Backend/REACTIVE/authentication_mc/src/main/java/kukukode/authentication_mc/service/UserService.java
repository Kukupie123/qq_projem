package kukukode.authentication_mc.service;

import kukukode.authentication_mc.entities.UserEntity;
import kukukode.authentication_mc.repo.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    final
    UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }


    /**
     * Returns JWT token on successful authentication
     *
     * @return token
     */
    public Mono<ResponseEntity<Boolean>> signIn(UserEntity user) {
        if (user.getEmail() == null || user.getCred() == null)
            return Mono.just(ResponseEntity.badRequest().body(false));
        var monoUser = repo.findById(user.getEmail());

        //Return appropriate mono after validating password
        return monoUser.map(userEntity -> {
            if (user.getCred().equals(userEntity.getCred()))
                //Correct credentials
                return ResponseEntity.ok().body(true);
            //Wrong credentials
            return ResponseEntity.badRequest().body(false);
        });

    }
}
