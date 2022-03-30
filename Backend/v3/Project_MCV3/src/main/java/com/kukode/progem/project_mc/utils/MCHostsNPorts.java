package com.kukode.progem.project_mc.utils;

public final class MCHostsNPorts {
    private MCHostsNPorts() {
    }

    public static final String AUTH_HOST = "${services.urls.auth}";
    public static final String AUTH_PORT = "${services.ports.auth}";

    public static final String PROJECT_HOST = "{services.urls.project}";
    public static final String PROJECT_PORT = "$services.ports.project}";

    public static final String PROJECTRULE_HOST = "${services.urls.projectrule}";
    public static final String PROJECTRULE_PORT = "${services.ports.projectrule}";

    public static final String MEMBER_HOST = "${services.urls.member}";
    public static final String MEMBER_PORT = "${services.ports.member}";
}
