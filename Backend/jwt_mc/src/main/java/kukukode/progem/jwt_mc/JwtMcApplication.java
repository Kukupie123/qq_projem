package kukukode.progem.jwt_mc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
public class JwtMcApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtMcApplication.class, args);
    }

}
