package kukukode.progem.projectleadermc.controller;

import kukukode.progem.projectleadermc.req_resp_model.AddMemberRequest;
import kukukode.progem.projectleadermc.response.BaseResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/projectleader")
public class MainController {

    ///Adds Leader to a project and returns the ID
    public Mono<BaseResponse> addMember(@RequestHeader("Authorization") String requesterUserID, @RequestBody AddMemberRequest requestBody) {
        /*
        1. Check if root project. If root project then check if requesterUserID matches the userID of the project Creator
        2. If not root project check if the requesterUserID is a leader of projectID
        **If all of it is correct we can now validate rule**
        4. If root project we don't need to do any checks as root project has max privilege
        5. If not root get projectRule using ruleID from projectID
        6. If all is correct we proceed
        7. The Primary key of a row is going to be projectID_memberType
         */

        //Get the project using projectID from request body

    }
}
