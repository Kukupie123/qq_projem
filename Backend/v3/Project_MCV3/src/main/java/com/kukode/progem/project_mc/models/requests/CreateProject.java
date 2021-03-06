package com.kukode.progem.project_mc.models.requests;


import com.kukode.progem.project_mc.models.entities.ProjectRuleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateProject {
    private String title; //Should not be null or empty
    private String desc; //Can be null
    private String visibility;//Should not be null or empty
    private String requesterID; // Will be null but will be modified internally to have a value
    private String tobeLeaderID;// May or may not be null, this person will be the leader of the newly created project. DOESN'T APPLY TO ROOT PROJECT. Root project will need to have both requesterID and tobeLeaderID same
    private ProjectRuleEntity projectRule;// Can be null for root project BUT HAS TO EXIST FOR SUB PROJECTS
    private int parentProjectID; //Will be -1 for root project. Will be other value for other parent

}
