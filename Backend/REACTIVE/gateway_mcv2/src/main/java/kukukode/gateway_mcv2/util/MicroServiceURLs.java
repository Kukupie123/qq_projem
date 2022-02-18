package kukukode.gateway_mcv2.util;

public final class MicroServiceURLs {
    private MicroServiceURLs() {
    }

    public static final String AUTH(String hostURL, int port) {
        return "http://" + hostURL + ":" + port + "/auth";
    }

    public static final String AUTH_SIGNIN = "/signin";

    public static final String JWT(String hostURL,int port) {
        return "http://" + hostURL + ":" + port + "/jwt";
    }

    public static String JWT_GENERATE(String id) {
        return "/generate/?id=" + id;
    }

    public static final String JWT_EXTRACT = "/getuserid";
}
