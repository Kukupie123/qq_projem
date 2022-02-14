package kukukode.progem.apigateway.service.microservice;

import kukukode.progem.apigateway.reqres.JwtMC.JwtMCResp;
import kukukode.progem.apigateway.util.OtherMCURLs;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JWTMCService {
    public String getUserID(String token) {
        String url = OtherMCURLs.JWT() + OtherMCURLs.JWT_GETUSERID() + OtherMCURLs.START_QUERYPARAM() + OtherMCURLs.JWT_PARAM_TOKEN();
        JwtMCResp resp = new RestTemplate().getForObject(url, JwtMCResp.class, token);
        return resp.getToken();
    }
}
