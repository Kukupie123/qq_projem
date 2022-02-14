package kukukode.progem.apigateway.service.microservice;

import kukukode.progem.apigateway.reqres.JwtMC.JwtMCResp;
import kukukode.progem.apigateway.util.PrivateMCURLs;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JWTMCService {
    public String getUserID(String token) {
        String url = PrivateMCURLs.JWT() + PrivateMCURLs.JWT_GETUSERID() + PrivateMCURLs.START_QUERYPARAM() + PrivateMCURLs.JWT_PARAM_TOKEN();
        JwtMCResp resp = new RestTemplate().getForObject(url, JwtMCResp.class, token);
        return resp.getToken();
    }
}
