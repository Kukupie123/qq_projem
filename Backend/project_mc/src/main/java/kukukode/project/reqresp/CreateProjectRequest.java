package kukukode.project.reqresp;


import lombok.Data;

@Data
public class CreateProjectRequest {
    private String title;
    private String desc;
    private String ancestry; //parent hierarchy
    private String rules; //JSON Format rules
}
