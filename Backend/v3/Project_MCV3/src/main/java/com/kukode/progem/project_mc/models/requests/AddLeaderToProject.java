package com.kukode.progem.project_mc.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddLeaderToProject {
    private String requesterID;//The user who requested
    private String toBeUserID; //The user who is to-be the leader
    private int projectID; //The projectID of the created project
}
