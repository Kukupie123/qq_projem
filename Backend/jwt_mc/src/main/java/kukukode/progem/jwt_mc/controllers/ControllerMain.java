package kukukode.progem.jwt_mc.controllers;


import kukukode.progem.jwt_mc.Model.ReqExtractUID;
import kukukode.progem.jwt_mc.Model.ReqGenerate;
import kukukode.progem.jwt_mc.Model.RespExtractUID;
import kukukode.progem.jwt_mc.Model.RespGenerate;
import kukukode.progem.jwt_mc.services.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/jwt")
public class ControllerMain {


    final
    JWTUtil jwtUtil;

    @Autowired
    public ControllerMain(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public ResponseEntity<RespGenerate> generateToken(@RequestBody ReqGenerate reqGenerate) {
        if (reqGenerate.getUserID() == null || reqGenerate.getUserID().isEmpty() || reqGenerate.getUserID().isBlank())
            return ResponseEntity.internalServerError().body(new RespGenerate("No userID provided"));
        String token = jwtUtil.generateToken(reqGenerate.getUserID());
        return ResponseEntity.ok(new RespGenerate(token));
    }

    @RequestMapping(value = "/getuid", method = RequestMethod.GET)
    public ResponseEntity<RespExtractUID> extractUserIDFromJWT(@RequestBody ReqExtractUID reqExtractUID) {
        String userID = null;
        try {
            userID = jwtUtil.extractUserName(reqExtractUID.getToken());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new RespExtractUID(e.getMessage() + " "));
        }
        return ResponseEntity.ok(new RespExtractUID(userID));
    }
}
