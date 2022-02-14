package kukukode.progem.authmicroservice.util;

public final class OtherMCURLs {
    private OtherMCURLs() {
    }

    public static String JWT() {
        return "http://localhost:1234/jwt";
    }

    public static String JWT_GENERATE() {

        return "/generate/";
    }
    public static final String JWT_GETUSERID() {
        return "/getuid";
    }
    public static final String START_QUERYPARAM() {
        return "?";
    }

    public static String JWT_PARAM_USERID() {
        return "userID={userID}";
    }


}
