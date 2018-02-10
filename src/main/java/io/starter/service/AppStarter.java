package io.starter.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 04.02.2018
 */
@SpringBootApplication
@EnableScheduling
public class AppStarter {
    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args);
    }
}
