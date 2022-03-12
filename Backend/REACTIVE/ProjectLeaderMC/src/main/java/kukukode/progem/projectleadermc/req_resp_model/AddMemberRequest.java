package kukukode.progem.projectleadermc.req_resp_model;

public class AddMemberRequest {
    String requesterUserID; //The user who requested
    String targetUserID; //The user who is to be added to the project
    String memberType; //Leader or member
    int projectID; //Used for various tasks such as checking ruleID to see if they are allowed to do so
}
