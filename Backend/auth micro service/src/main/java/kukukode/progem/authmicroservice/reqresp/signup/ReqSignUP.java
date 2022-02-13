package kukukode.progem.authmicroservice.reqresp.signup;

import lombok.Data;

@Data
public class ReqSignUP {
    //Every request needs an empty constructor
    String email;
    String password;
}
