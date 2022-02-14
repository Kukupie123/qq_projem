package kukukode.progem.jwt_mc.Model;

import lombok.Data;

@Data
public class RespGenerate {
    String token;

    public RespGenerate(String token) {
        this.token = token;
    }

    public RespGenerate() {
    }
}
