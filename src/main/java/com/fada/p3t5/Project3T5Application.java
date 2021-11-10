package com.fada.p3t5;

import com.fada.p3t5.client.MainClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Project3T5Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Project3T5Application.class, args);
        MainClient mainClient = context.getBean(MainClient.class);
        // We need to block for the content here or the JVM might exit before the message is logged
        System.out.println(">> message = " + mainClient.getGreeting().block());
    }
}
