package kukukode.progem.authmicroservice.controllers;


import kukukode.progem.authmicroservice.reqresp.signup.ReqSignUP;
import kukukode.progem.authmicroservice.reqresp.signup.RespSignUP;
import kukukode.progem.authmicroservice.services.JWTUtil;
import kukukode.progem.authmicroservice.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/auth")
public class MainController {

    final UserServiceImp userServiceImp;
    final AuthenticationManager authenticationManager;
    final JWTUtil jwtUtil;

    @Autowired
    public MainController(UserServiceImp userServiceImp, AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.userServiceImp = userServiceImp;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<RespSignUP> signup(@RequestBody ReqSignUP reqSignUP) {
        boolean success = userServiceImp.signUP(reqSignUP.getEmail(), reqSignUP.getPassword());
        if (success)
            return ResponseEntity.ok(new RespSignUP("Success"));
        else
            return ResponseEntity.status(403).body(new RespSignUP("Failed to signup"));


    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<RespSignUP> signIN(@RequestBody ReqSignUP reqSignUP) {

        try {
            //If this section doesn't throw exception we know that the authentication has been successful
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(reqSignUP.getEmail(), reqSignUP.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(405).body(new RespSignUP("Invalid credentials"));
        }
        String token = jwtUtil.generateToken(reqSignUP.getEmail());
        return ResponseEntity.ok(new RespSignUP(token));
    }
}
