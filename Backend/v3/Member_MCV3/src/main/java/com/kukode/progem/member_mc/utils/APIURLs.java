package com.kukode.progem.member_mc.utils;

public final class APIURLs {
    private APIURLs() {
    }

    public static final String AUTH_BASE = "${auth.base}";
    public static final String AUTH_SIGNUP = "${auth.signup}";
    public static final String AUTH_SIGNIN = "${auth.signin}";

    public static final String JWT_BASE = "${jwt.base}";
    public static final String JWT_GETUSERID = "${jwt.getuserid}";

    public static final String PROJECT_BASE = "${project.base}";

    public static final String PROJECTRULE_Base = "${projectrule.base}";
    public static final String PROJECTRULE_GETSIMILAR = "${projectrule.getsimilarrule}";

    public static final String MEMBER_BASE = "${member.base}";
    public static final String MEMBER_ADDLEADER = "${member.addleader}";
}
