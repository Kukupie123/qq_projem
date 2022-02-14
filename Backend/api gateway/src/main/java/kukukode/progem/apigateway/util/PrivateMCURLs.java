package kukukode.progem.apigateway.util;

public final class PrivateMCURLs {
    private PrivateMCURLs() {
    }

    public static String JWT() {
        return "http://localhost:1234/jwt";
    }

    public static String JWT_GENERATE() {

        return "/generate/";
    }

    public static String JWT_GETUSERID() {
        return "/getuid";
    }

    public static String START_QUERYPARAM() {
        return "?";
    }

    public static String JWT_PARAM_USERID() {
        return "userID={userID}";
    }

    public static String JWT_PARAM_TOKEN() {
        return "token={token}";
    }
}
