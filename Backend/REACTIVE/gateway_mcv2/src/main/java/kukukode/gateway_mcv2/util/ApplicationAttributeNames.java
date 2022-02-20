package kukukode.gateway_mcv2.util;

public final class ApplicationAttributeNames {
    private ApplicationAttributeNames() {
    }

    public static final String HOSTURL_AUTH = "${services.hosts.auth}";
    public static final String HOSTURL_JWT = "${services.hosts.jwt}";
    public static final String HOSTURL_PROJECT = "${services.hosts.project}";
    public static final String HOSTURL_PROJECTRULE = "${services.hosts.projectrule}";

    public static final String PORT_AUTH = "${services.ports.auth}";
    public static final String PORT_JWT = "${services.ports.jwt}";
    public static final String PORT_PROJECT = "${services.ports.project}";
    public static final String PORT_PROJECTRULE = "${services.ports.projectrule}";


}
