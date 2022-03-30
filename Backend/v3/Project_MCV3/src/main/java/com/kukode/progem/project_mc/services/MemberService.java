package com.kukode.progem.project_mc.services;

import com.kukode.progem.project_mc.models.BaseResponse;
import com.kukode.progem.project_mc.models.requests.AddLeaderToProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MemberService {

    String memberPost;
    String memberHost;

    String memberBase;
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
        client.post()
                .bodyValue(body)
                .exchangeToMono(clientResponse -> {
                });
        return null;
    }
}
