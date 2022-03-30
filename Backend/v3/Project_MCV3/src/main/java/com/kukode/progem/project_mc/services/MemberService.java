package com.kukode.progem.project_mc.services;

import com.kukode.progem.project_mc.models.BaseResponse;
import com.kukode.progem.project_mc.models.requests.AddLeaderToProject;
import com.kukode.progem.project_mc.utils.APIURLs;
import com.kukode.progem.project_mc.utils.MCHostsNPorts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MemberService {
    @Value(MCHostsNPorts.MEMBER_PORT)
    String memberPost;
    @Value(MCHostsNPorts.MEMBER_HOST)
    String memberHost;
    @Value(APIURLs.MEMBER_BASE)
    String memberBase;
    @Value(APIURLs.MEMBER_ADDLEADER)
    String memberAddLeader;

    Logger log = LoggerFactory.getLogger("Member service");

    public Mono<ResponseEntity<BaseResponse<Void>>> addLeader(int projectID, String requesterID, String tobeLeaderID) {
        String url = "http://" + memberHost + ":" + memberPost + "/" + memberBase + "/" + memberAddLeader;
        log.info("add leader called with {} on {}", projectID, url);

        AddLeaderToProject body = new AddLeaderToProject();
        body.setRequesterID(requesterID);
        body.setToBeUserID(tobeLeaderID);
        body.setProjectID(projectID);

        WebClient client = WebClient.create(url);
        return client.post()
                .bodyValue(body)
                .exchangeToMono(clientResponse -> {
                    var response = clientResponse.bodyToMono(BaseResponse.class);
                    return response.flatMap(baseResponse -> Mono.just(ResponseEntity.status(clientResponse.statusCode()).body(baseResponse))
                    );
                });
    }
}
