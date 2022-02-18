package kukukode.project.util;

public final class PrivateMCURLs {
    private PrivateMCURLs() {
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

    public static String JWT_PARAM_TOKEN() {
        return "token={token}";
    }


    public static String RULE() {
        return "http://localhost:5000/rules";
    }

    public static final String RULE_= "";
}
