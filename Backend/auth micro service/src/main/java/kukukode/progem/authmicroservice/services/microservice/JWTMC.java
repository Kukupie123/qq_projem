package kukukode.progem.authmicroservice.services.microservice;


import kukukode.progem.authmicroservice.reqresp.jwt.GenerateResponse;
import kukukode.progem.authmicroservice.util.OtherMCURLs;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class JWTMC {
    public String generateToken(String userID) {
        Map<String, String> request = new HashMap<>();
        request.put("userID", userID);
        //URL, REQUEST, Response type
        GenerateResponse resp = new RestTemplate().postForObject(OtherMCURLs.JWT() + OtherMCURLs.JWT_GENERATE(), request, GenerateResponse.class);
        return resp.getToken();
    }
}
