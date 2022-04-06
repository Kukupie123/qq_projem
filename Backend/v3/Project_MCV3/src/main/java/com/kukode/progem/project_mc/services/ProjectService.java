package com.kukode.progem.project_mc.services;

import com.kukode.progem.project_mc.models.BaseResponse;
import com.kukode.progem.project_mc.models.entities.Project;
import com.kukode.progem.project_mc.models.entities.ProjectRuleEntity;
import com.kukode.progem.project_mc.repo.ProjectRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {
    final ProjectRepo projectRepo;
    final MemberService memberService;
    Logger log = LoggerFactory.getLogger("Project Service");

    public ProjectService(ProjectRepo projectRepo, MemberService memberService) {
        this.projectRepo = projectRepo;
        this.memberService = memberService;
    }

    /**
     * Creates a ruleEntity with root privilege
     */
    public ProjectRuleEntity createRootProjectRule(String visibility) throws Exception {
        log.info("CreateRootProjectRule called with visibility {}", visibility);

        if (visibility.trim().equalsIgnoreCase("private") || visibility.trim().equalsIgnoreCase("public")) {

        } else {
            log.info("Invalid visibility!");
            throw new Exception("Visibility is not private nor public");
        }
        var rule = new ProjectRuleEntity();
        rule.setVisibility(visibility);
        rule.setChild_visibilities_allowed("*");
        rule.setHave_children(true);
        rule.setHave_children_needs_permission(false);
        rule.setCan_leader_task_member(true);
        rule.setTask_member_visibilities_allowed("*");
        rule.setTask_member_needs_permission(false);
        rule.setTask_member_complete_permission(false);
        rule.setCan_leader_task_children(true);
        rule.setTask_child_visibilities_allowed("*");
        rule.setTask_child_needs_permission(false);
        rule.setTask_child_complete_permission(false);
        rule.setCan_leader_task_foreign(true);
        rule.setTask_foreign_visibilities_allowed("*");
        rule.setTask_foreign_needs_permission(false);
        rule.setTask_foreign_complete_permission(false);
        rule.setId(-69); // dummy ID. Will Never be used
        return rule;
    }

    /**
     * Create project and return it back
     *
     * @param project      The Project entity to be created
     * @param requesterID  The userID of the requester
     * @param toBeLeaderID The userID of the user who is going to be added as the leader of the project
     * @return Project Entity that has been created
     */
    public Mono<ResponseEntity<BaseResponse<Project>>> createProject(Project project, String requesterID, String toBeLeaderID) {
        var savedProjectMono = projectRepo.save(project);
        return savedProjectMono.flatMap(savedProject -> {
            //Created project successfully Now we need to save the user as the leader
            Mono<ResponseEntity<BaseResponse<Void>>> leaderMono = memberService.addLeader(savedProject.getId(), requesterID, toBeLeaderID);

            //We get Mono<ResponseEntity<BaseResponse<Project>>> from "leaderMono.map" lambda
            return leaderMono.map(memberRes -> {
                        if (!memberRes.getStatusCode().is2xxSuccessful())
                            return ResponseEntity.status(memberRes.getStatusCode()).body(new BaseResponse<Project>(null, memberRes.getBody().getMessage()));
                        return ResponseEntity.ok().body(new BaseResponse<Project>(savedProject, "successfully created project, and added user as leader"));
                    }
            );
        });

    }

    public Mono<Project> getProject(int id) {
        log.info("getProject called with id {}", id);
        return projectRepo.findById(id)
                .defaultIfEmpty(new Project(-1, "", "", "", null, false, -1, null));
    }


    /**
     * Adds ancestry to base ancestry and returns it. FOR Root PROJECTS PASS parentProjectID as -1
     *
     * @param parentsAncestry    The Ancestry for the parent. For root projects this will be "-" or empty string
     * @param parentProjectID The ID of the parentProject. For Root projects this will be -1
     * @return a string with the new ancestry tree. Eg: ("1-4",6) will return 1-4-6
     */
    public String addAncestry(String parentsAncestry, int parentProjectID) throws Exception {
        //1.Check if parentProjectID <0. If yes then it is a root project and we can directly return without any further checks
        if (parentProjectID < 0) return "-";

        //2.ParentProjectID is valid meaning it does have a parent
        //split the ancestry into parts
        String[] ancestriesRAW = parentsAncestry.split("-");
        List<String> ancestries = new ArrayList<String>(Arrays.asList(ancestriesRAW));

        ancestries.removeIf(s -> s.isEmpty() || s.isBlank());

        //Throw error if we already have a ancestry with parentID supplied
        for (String s : ancestries) {
            log.info("value of s while purging is {}", s);
            if (s.equals(Integer.toString(parentProjectID))) {
                throw new Exception("ProjectID already part of ancestry tree");
            }
        }

        //Now we have refined ancestry tree for base
        String ancestry = "";
        for (int i = 0; i < ancestries.size(); i++) {
            //Check if it is last index
            if ((i + 1) == ancestries.size()) {
                //last so we need to not add - at the end
                if (parentProjectID >= 0)
                    ancestry = ancestry + ancestries.get(i) + "-" + parentProjectID; //adding the parentProjectId as the parent
                else {
                    ancestry = ancestry + ancestries.get(i); //parentID is invalid meaning it has no parent i.e this is a root project and SHOULD NEVER REACH THIS
                    log.error("THIS LOOP WAS NEVER MEANT TO BE REACHED FOR A ROOT PROJECT AS IT SHOULD NOT HAVE ANY PARENT");
                }
            } else {
                //not last so we can add -
                ancestry = ancestry + ancestries.get(i) + "-";
            }
        }

        if (ancestry.isEmpty()) {
            //Base ancestry had no parent
            ancestry = Integer.toString(parentProjectID);
        }

        log.info("Ancestry finalized with value {}", ancestry);
        return ancestry;

    }
}
