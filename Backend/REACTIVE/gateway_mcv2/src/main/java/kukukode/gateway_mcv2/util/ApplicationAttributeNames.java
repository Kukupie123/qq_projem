package kukukode.gateway_mcv2.util;

public final class ApplicationAttributeNames {
    private ApplicationAttributeNames() {
    }

    public static final String HOSTURL_AUTH = "${services.urls.auth}";
    public static final String HOSTURL_JWT = "${services.urls.jwt}";
    public static final String HOSTURL_PROJECT = "${services.urls.project}";
    public static final String HOSTURL_PROJECTRULE = "${services.urls.projectrule}";

    public static final String PORT_AUTH = "${services.ports.auth}";
    public static final String PORT_JWT = "${services.ports.jwt}";
    public static final String PORT_PROJECT = "${services.ports.project}";
    public static final String PORT_PROJECTRULE = "${services.ports.projectrule}";

}
