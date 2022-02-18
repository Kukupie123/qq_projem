package kukukode.project.service.microservice;

import kukukode.project.reqresp.microservice.JwtMCResp;
import kukukode.project.util.PrivateMCURLs;
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
