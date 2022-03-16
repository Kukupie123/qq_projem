package kukukode.gateway_mcv2;

import kukukode.gateway_mcv2.util.ApplicationAttributeNames;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayMcv2Application {


    public static void main(String[] args) {
        SpringApplication.run(GatewayMcv2Application.class, args);
    }

}
