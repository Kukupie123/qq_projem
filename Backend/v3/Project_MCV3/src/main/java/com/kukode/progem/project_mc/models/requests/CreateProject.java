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
    private String userID; // Will be null but will be modified internally to have a value

}
