package kukukode.progem.authmicroservice.services;

import kukukode.progem.authmicroservice.jpaEntity.User;
import kukukode.progem.authmicroservice.jpaRepo.UserRepository;
import kukukode.progem.authmicroservice.reqresp.signin.RespSignIN;
import kukukode.progem.authmicroservice.reqresp.signup.RespSignUP;
import kukukode.progem.authmicroservice.services.microservice.JWTMC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {


    final
    UserRepository repo;
    final AuthenticationManager authenticationManager;
    final JWTMC jwtmcService;

    @Autowired
    public UserServiceImp(UserRepository repo, AuthenticationManager authenticationManager, JWTMC jwtmcService) {
        this.repo = repo;
        this.authenticationManager = authenticationManager;
        this.jwtmcService = jwtmcService;
    }

    @Override
    public ResponseEntity<RespSignUP> signUP(String email, String password) {
        //Hash the password in production
        try {
            repo.save(new User(email, password));
            return ResponseEntity.ok(new RespSignUP("Success"));
        } catch (Exception e) {
            return ResponseEntity.status(403).body(new RespSignUP(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<RespSignIN> signIN(String email, String password) {
        try {
            //If this section doesn't throw exception we know that the authentication has been successful
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(403).body(new RespSignIN(e.getMessage()));
        }
        //Calling another MC to generate token
        return ResponseEntity.ok(new RespSignIN(jwtmcService.generateToken(email)));
    }

    @Override
    public Optional<User> getUser(String email) {
        return repo.findById(email);
    }


}
