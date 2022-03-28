package com.kukode.progem.member_mc.controllers;

import com.kukode.progem.member_mc.models.BaseResponse;
import com.kukode.progem.member_mc.models.entities.Member;
import com.kukode.progem.member_mc.models.entities.Project;
import com.kukode.progem.member_mc.models.requests.AddLeaderToProject;
import com.kukode.progem.member_mc.services.MemberService;
import com.kukode.progem.member_mc.services.ProjectService;
import com.kukode.progem.member_mc.utils.APIURLs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/" + APIURLs.MEMBER_BASE)
public class MemberController {
    final Logger log = LoggerFactory.getLogger("Member Controller");

    final ProjectService projectService;
    final MemberService memberService;

    public MemberController(ProjectService projectService, MemberService memberService) {
        this.projectService = projectService;
        this.memberService = memberService;
    }

    public Mono<ResponseEntity<BaseResponse<Void>>> addRootLeader(@RequestBody AddLeaderToProject body) {
        /*
        Validations to perform:
        1. If Root project, requester and to-be leader has to be the same
        2. If not root project, check if requester is a leader of parent project and if parent project rule allows adding leaders or if only root leader can add
        3. If only root leader can add then check IF requester is the root leader
        4. Optional : JWT token based to be 99% secure but maybe in future as I plan on making this Service private and can only be accessed internally
         */

        //Get project
        Mono<Project> projectMono = projectService.getProjectFromID(body.getProjectID());
        return projectMono.defaultIfEmpty(null)
                .flatMap(project ->
                        {
                            if (project == null)
                                return Mono.just(ResponseEntity.status(404).body(new BaseResponse<Void>(null, "Project not found in record")));
                            //Found project, we can proceed
                            //Check if project is root
                            if (
                                    project.getAncestry() == null ||
                                            project.getAncestry().trim().isEmpty() ||
                                            project.getAncestry().replace("-", "").trim().isEmpty()
                            ) {
                                //Root project,Check if requester and to-be id is same
                                if (!body.getRequesterID().equals(body.getToBeUserID()))
                                    return Mono.just(ResponseEntity.badRequest().body(new BaseResponse<Void>(null, "Requester and To-be leader has to be the same for Root project")));

                                //requester and project are same
                                //check if memberMono row already exist for the project
                                String memberID = body.getProjectID() + "_" + body.getToBeUserID();
                                Mono<Member> memberMono = memberService.getMemberEntityFromDB(memberID);

                                //Save the to-be to the memberMono entity and update it
                                memberMono.flatMap(member -> {
                                    if (member.getMembers().trim().isEmpty())
                                        member.setMembers(body.getToBeUserID());
                                    else member.setMembers(member.getMembers() + "-" + body.getToBeUserID());
                                    return Mono.just(memberService.updateMember(member)
                                            .flatMap(member1 -> Mono.just(ResponseEntity
                                                    .ok(new BaseResponse<Void>(null, "added user as leader of " + memberID))
                                            )));
                                });

                            }
                            //Not root project.
                            return Mono.just(ResponseEntity.unprocessableEntity().body(new BaseResponse<Void>(null, "Adding leader to sub project is WIP")));

                        }
                );
    }

}
