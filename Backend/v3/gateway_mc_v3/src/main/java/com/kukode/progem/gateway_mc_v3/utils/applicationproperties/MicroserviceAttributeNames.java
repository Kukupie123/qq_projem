package com.kukode.progem.gateway_mc_v3.utils.applicationproperties;

public final class MicroserviceAttributeNames {
    private MicroserviceAttributeNames() {
    }

    public static final String AUTH_HOST = "${services.urls.auth}";
    public static final String AUTH_PORT = "${services.ports.auth}";
}
