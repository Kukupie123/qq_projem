package com.kukode.progem.gateway_mc_v3.models.requests;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
/**
 * Model of the payload that is to be sent by the client when sign-in/up is requested
 */

public class SignInUp {
    private String email;
    private String password;
}
