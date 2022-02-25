package kukukode.authentication_mc.controller;


import kukukode.authentication_mc.entities.UserEntity;
import kukukode.authentication_mc.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public Mono<ResponseEntity<Boolean>> signin(@RequestBody UserEntity userEntity) {
        return userService.signIn(userEntity);
    }

    @PostMapping("/signup")
    public Mono<ResponseEntity<String>> signup(@RequestBody UserEntity user) {
        return userService.signup(user);
    }

}
