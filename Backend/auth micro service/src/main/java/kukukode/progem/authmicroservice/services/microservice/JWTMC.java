package kukukode.progem.authmicroservice.services.microservice;


import kukukode.progem.authmicroservice.reqresp.jwtMC.JwtMCResp;
import kukukode.progem.authmicroservice.util.OtherMCURLs;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JWTMC {
    public String generateToken(String userID) {
        String url = OtherMCURLs.JWT() + OtherMCURLs.JWT_GENERATE() + OtherMCURLs.START_QUERYPARAM() + OtherMCURLs.JWT_PARAM_USERID();
        //localhost:3000/jwt/?userID={userID}
        JwtMCResp resp = new RestTemplate().getForObject(url, JwtMCResp.class, userID);
        return resp.getToken();
    }
}

