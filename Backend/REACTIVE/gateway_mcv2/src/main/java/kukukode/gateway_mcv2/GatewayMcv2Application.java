package kukukode.gateway_mcv2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayMcv2Application {
    @Value("${server.port}")
    static int link;
    public static void main(String[] args) {
        System.out.println(link);
        SpringApplication.run(GatewayMcv2Application.class, args);
    }

}
