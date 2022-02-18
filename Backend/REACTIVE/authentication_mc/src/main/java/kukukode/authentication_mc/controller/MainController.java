package kukukode.authentication_mc.controller;


import kukukode.authentication_mc.entities.UserEntity;
import kukukode.authentication_mc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class MainController {

    final
    UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public Mono<Boolean> signin(@RequestBody UserEntity userEntity) {
        return userService.signIn(userEntity);
    }
}
