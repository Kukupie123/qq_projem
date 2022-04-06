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
import org.springframework.web.bind.annotation.RequestMethod;
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


    /*
    TODO://Retrieves the projectID based on the ProjectID in the payload.
    Determines if it's a root project or not. If root project the requesterID has to be the userID of the project
    If the project is not a root. Get paren project rule and see if it is allowed to add members. If allowed then check if requester is leader.
    If not leader check if requester is root project leader
    If leader of parent or root and parent allows then add leader
    else do not
     */

    /**
     * @param body RequesterID : The userID of the requester, ProjectID : The projectID of the project to add leader to, ToBeLeaderID : The userID of the user who is going to be the leader
     * @return 200 ok status code with no body
     */
    @RequestMapping(value = "/" + APIURLs.MEMBER_ADDLEADER, method = RequestMethod.POST)
    public Mono<ResponseEntity<BaseResponse<Void>>> addLeader(@RequestBody AddLeaderToProject body) {
        log.info("AddleaderToProject with body {}", body);

        //1.Get project
        Mono<Project> projectMono = projectService.getProjectFromID(body.getProjectID());
        return projectMono.defaultIfEmpty(new Project(true))
                .flatMap(project ->
                        {
                            if (!project.isValid())
                                return Mono.just(ResponseEntity.status(404).body(new BaseResponse<Void>(null, "Project not found in record")));
                            //Found project, we can proceed

                            //2.Check if project is root, The requesterID has to be same as userID of the project
                            if (project.getAncestry().split("-").length <= 0) {
                                //Root project

                                //Check if requesterID matches userID of project
                                if (!body.getRequesterID().equalsIgnoreCase(project.getUserid()))
                                    return Mono.just(ResponseEntity.internalServerError().body(new BaseResponse<Void>(null, "Requester and User who created the project DO NOT match.")));

                                //requester and userID of project are same, we can proceed
                                //3.check if memberMono row already exist for the project
                                String memberID = body.getProjectID() + "_leader";
                                Mono<Member> memberMono = memberService.getMemberEntityFromDB(memberID);

                                //Save the to-be to the memberMono entity and update it
                                return memberMono.flatMap(member -> {
                                            //check if user is already part of the record
                                            for (String s : member.getMembers().split("-")) {
                                                if (s.trim().equalsIgnoreCase(body.getToBeUserID())) {
                                                    return Mono.just(ResponseEntity.badRequest().body(new BaseResponse<>(null, "User is already a leader of ProjectID: " + body.getProjectID())));
                                                }
                                            }
                                            if (member.getMembers().trim().isEmpty())
                                                member.setMembers(body.getToBeUserID());
                                            else member.setMembers(member.getMembers() + "-" + body.getToBeUserID());
                                            member.setNew(false);//To update it
                                            return memberService.updateMember(member)
                                                    .flatMap(member1 -> Mono.just(ResponseEntity
                                                            .ok(new BaseResponse<Void>(null, "added user as leader of " + memberID))
                                                    ));
                                        }
                                );

                            }
                            //Not root project
                            //2. Get parent project
                            try {
                                int parentProjectID = projectService.getParentBasedOnAncestry(project.getAncestry());

                                //3. Get parent project
                                var parentProjectMono = projectService.getProjectFromID(parentProjectID);
                                parentProjectMono.flatMap(
                                        parentProject -> {
                                            if (!parentProject.isValid())
                                                return Mono.just(ResponseEntity.status(404).body(new BaseResponse<Void>(null, "Parent Project not found!")));
                                            //Parent project found

                                            //4. Get the rule of the parent project
                                            //5. Check if rule allows parent project to have children
                                            //6. Check if requester is root user
                                            //7. Check if requester is leader of parent project
                                        }
                                );
                            } catch (Exception e) {
                                return Mono.just(ResponseEntity.internalServerError().body(new BaseResponse<Void>(null, e.getMessage())));
                            }


                        }
                );
    }

}
