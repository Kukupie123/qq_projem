package kukukode.progem.projectleadermc.util;

public final class MicroserviceURLs {
    private MicroserviceURLs() {
    }

    //Project
    public static String PROJECT(String hostURL, int port) {
        return "http://" + hostURL + ":" + port + "/project";
    }
}
