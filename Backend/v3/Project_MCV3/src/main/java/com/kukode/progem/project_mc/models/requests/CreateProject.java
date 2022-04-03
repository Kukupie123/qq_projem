package com.kukode.progem.project_mc.models.requests;


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
    private String ancestry; //can be null
    private String requesterID; // Will be null but will be modified internally to have a value
    private String tobeLeaderID;// May or may not be null, this person will be the leader of the newly created project. DOESN'T APPLY TO ROOT PROJECT. Root project will need to have both requesterID and tobeLeaderID same

}
