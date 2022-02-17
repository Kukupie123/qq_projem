package kukukode.progem.jwt_mc.controllers;


import kukukode.progem.jwt_mc.Model.RespExtractUID;
import kukukode.progem.jwt_mc.Model.RespGenerate;
import kukukode.progem.jwt_mc.services.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/jwt")
public class ControllerMain {


    final
    JWTUtil jwtUtil;


    @Autowired
    public ControllerMain(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    //Attribute names
    private final String userIDParam = "userID";
    private final String tokenParam = "token";

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public ResponseEntity<RespGenerate> generateToken(@RequestParam(name = userIDParam) String userID) {
        if (userID == null || userID.isEmpty() || userID.isBlank())
            return ResponseEntity.internalServerError().body(new RespGenerate("No userID provided"));
        String token = jwtUtil.generateToken(userID);
        return ResponseEntity.ok(new RespGenerate(token));
    }

    @RequestMapping(value = "/getuid", method = RequestMethod.GET)
    public ResponseEntity<RespExtractUID> extractUserIDFromJWT(@RequestParam(name = tokenParam) String token) {
        String userID;
        try {
            userID = jwtUtil.extractUserName(token);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new RespExtractUID(e.getMessage() + " "));
        }
        return ResponseEntity.ok(new RespExtractUID(userID));
    }
}
