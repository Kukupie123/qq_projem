package kukukode.progem.authmicroservice.services;

import kukukode.progem.authmicroservice.jpaEntity.User;

import java.util.Optional;

public interface UserService {

    boolean signUP(String email, String password);


    String signIN(String email, String password);


    Optional<User> getUser(String email);



}
