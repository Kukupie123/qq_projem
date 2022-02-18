package kukukode.gateway_mcv2.util;

public final class MicroServiceURLs {
    private MicroServiceURLs() {
    }

    public static final String AUTH = "http://localhost:2000/auth";
    public static final String AUTH_SIGNIN = "/signin";

    public static final String JWT = "http://localhost:3000/jwt";

    public static final String JWT_GENERATE(String id) {
        return "/generate/?id=" + id;
    }
}
