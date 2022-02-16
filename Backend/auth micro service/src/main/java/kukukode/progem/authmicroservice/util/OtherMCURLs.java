package kukukode.progem.authmicroservice.util;

public final class OtherMCURLs {
    private OtherMCURLs() {
    }

    public static String JWT() {
        return "http://localhost:3000/jwt";
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


}
