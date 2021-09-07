package com.forsrc.jredmine.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan("com.forsrc.**")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class JredmineServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JredmineServerApplication.class, args);
	}

}
