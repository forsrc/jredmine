package com.forsrc.jredmine.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching()
public class JredmineServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JredmineServerApplication.class, args);
    }

}
