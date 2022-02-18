package kukukode.gateway_mcv2.service;

import kukukode.gateway_mcv2.entities.UserEntity;
import kukukode.gateway_mcv2.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    @Autowired
    UserRepo repo;


    /**
     * Returns JWT token on successful authentication
     *
     * @return token
     */
    public Mono<UserEntity> signIn(UserEntity user) {
        return repo.findById(user.getEmail());

    }
}
