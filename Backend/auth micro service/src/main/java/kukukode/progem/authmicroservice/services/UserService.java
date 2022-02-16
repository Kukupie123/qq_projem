package kukukode.progem.authmicroservice.services;

import kukukode.progem.authmicroservice.jpaEntity.User;
import kukukode.progem.authmicroservice.reqresp.signin.RespSignIN;
import kukukode.progem.authmicroservice.reqresp.signup.RespSignUP;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Optional;

public interface UserService {

    ResponseEntity<RespSignUP> signUP(String email, String password);


    ResponseEntity<RespSignIN> signIN(String email, String password, AuthenticationManager authenticationManager);


    Optional<User> getUser(String email);



}
