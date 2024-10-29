package com.spring.eduConnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class EduConnectApplication {

    public static void main(String[] args) {

//        SpringApplication.run(EduConnectApplication.class, args);

        ApplicationContext context = SpringApplication.run(EduConnectApplication.class, args);
        Environment env = context.getEnvironment();

        String[] activeProfiles = env.getActiveProfiles();
        if (activeProfiles.length > 0) {
            System.out.println("Active Profile: " + activeProfiles[0]);
        } else {
            System.out.println("No active profile, defaulting to 'default'");
        }
    }

}
