package kukukode.progem.jwt_mc.Model;

import lombok.Data;

@Data
public class RespExtractUID {
    private String uid;

    public RespExtractUID() {
    }

    public RespExtractUID(String uid) {
         this.uid = uid;
    }
}
