package kukukode.gateway_mcv2.req_resp_model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReqModelCreateRootProject {
    private String title;
    private String desc;
    private String visibility;
}
