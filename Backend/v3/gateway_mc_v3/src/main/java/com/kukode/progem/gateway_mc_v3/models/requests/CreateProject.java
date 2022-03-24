package com.kukode.progem.gateway_mc_v3.models.requests;


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

}
