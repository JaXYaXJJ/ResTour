package edu.hackeru.evgenyzakalinsky.restour;

import edu.hackeru.evgenyzakalinsky.restour.config.JWTConfig;
import edu.hackeru.evgenyzakalinsky.restour.error.PackageException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JWTConfig.class)
public class RestourApplication {

    public static void main(String[] args) {

        try {
            SpringApplication.run(RestourApplication.class, args);
        } catch (PackageException p) {

        }
    }
}
