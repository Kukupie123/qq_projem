package kukukode.progem.authmicroservice.reqresp.signin;


import lombok.Data;

@Data
public class RespSignIN {
    String token;

    public RespSignIN(String token) {
        this.token = token;
    }
}
