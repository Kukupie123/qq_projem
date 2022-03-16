package kukukode.gateway_mcv2.util;

import java.security.spec.PSSParameterSpec;

public final class MicroServiceURLs {
    private MicroServiceURLs() {
    }
    //Auth

    public static String AUTH(String hostURL, int port) {
        return "http://" + hostURL + ":" + port + "/auth";
    }

    public static final String AUTH_SIGNIN = "/signin";

    public static final String AUTH_SIGNUP = "/signup";

    //JWT

    public static String JWT(String hostURL, int port) {
        return "http://" + hostURL + ":" + port + "/jwt";
    }

    public static String JWT_GENERATE(String id) {
        return "/generate/?id=" + id;
    }

    public static final String JWT_EXTRACT = "/getuserid";

    //Project

    public static String PROJECT(String hostURL, int port) {
        return "http://" + hostURL + ":" + port + "/project";
    }

    public static final String PROJECT_CREATE_ROOT = "/root";

    //Rule

    public static String RULE(String hostURL, int port) {
        return "http://" + hostURL + ":" + port + "/projectrule";
    }

    public static final String RULE_GETRULE = "/";
}
