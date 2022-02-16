package kukukode.progem.authmicroservice.controllers;


import kukukode.progem.authmicroservice.reqresp.signin.RespSignIN;
import kukukode.progem.authmicroservice.reqresp.signup.ReqSignUP;
import kukukode.progem.authmicroservice.reqresp.signup.RespSignUP;
import kukukode.progem.authmicroservice.services.UserServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/auth")
public class MainController {

    final UserServiceImp userServiceImp;

    public MainController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<RespSignUP> signup(@RequestBody ReqSignUP reqSignUP) {
        return userServiceImp.signUP(reqSignUP.getEmail(), reqSignUP.getPassword());

    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<RespSignIN> signIN(@RequestBody ReqSignUP reqSignUP) {
        return userServiceImp.signIN(reqSignUP.getEmail(), reqSignUP.getPassword());
    }
}
