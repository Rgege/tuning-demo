package com.allen.tuning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TuningDemoApplication {

    public static ApplicationContext context;

    public static void main(String[] args) {
        String[] devArgs = new String[1];
        devArgs[0] = "--spring.profiles.active=dev";
        if (0 == args.length) {
            context=SpringApplication.run(TuningDemoApplication.class, devArgs);
        } else {
            context=SpringApplication.run(TuningDemoApplication.class, args);
        }
        System.out.println(1);
    }
}
