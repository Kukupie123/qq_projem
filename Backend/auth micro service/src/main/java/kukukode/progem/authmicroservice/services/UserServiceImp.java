package kukukode.progem.authmicroservice.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kukukode.progem.authmicroservice.jpaEntity.User;
import kukukode.progem.authmicroservice.jpaRepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {


    final
    UserRepository repo;
    final JWTUtil jwtUtil;

    @Autowired
    public UserServiceImp(UserRepository repo, JWTUtil jwtUtil) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean signUP(String email, String password) {
        //Hash the password in production
        repo.save(new User(email, password));
        return true;
    }

    @Override
    public String signIN(String email, String password) {
        return "null";
    }

    @Override
    public Optional<User> getUser(String email) {
        return repo.findById(email);
    }




}
