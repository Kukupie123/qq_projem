package kukukode.progem.authmicroservice.services.microservice;


import kukukode.progem.authmicroservice.reqresp.jwt.GenerateResponse;
import kukukode.progem.authmicroservice.util.OtherMCURLs;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JWTMC {
    public String generateToken(String userID) {
        String url = OtherMCURLs.JWT() + OtherMCURLs.JWT_GENERATE() + OtherMCURLs.START_QUERYPARAM() + OtherMCURLs.JWT_PARAM_USERID();
        //localhost:1234/jwt/?userID={userID}
        GenerateResponse resp = new RestTemplate().getForObject(url, GenerateResponse.class, userID);
        return resp.getToken();
    }
}

