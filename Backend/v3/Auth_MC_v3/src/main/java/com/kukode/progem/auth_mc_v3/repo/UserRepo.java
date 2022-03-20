package com.kukode.progem.auth_mc_v3.repo;

import com.kukode.progem.auth_mc_v3.models.entities.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepo extends ReactiveCrudRepository<User, String> {

    @Query("SELECT * FROM users WHERE email = :email AND cred = :password")
    <S extends User> Mono<S> signIn(@Param("email") String email, @Param("password") String password);
}
