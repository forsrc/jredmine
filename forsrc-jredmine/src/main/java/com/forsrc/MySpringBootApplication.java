package com.forsrc;

import java.io.IOException;

//import de.codecentric.boot.admin.config.EnableAdminServer;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
@ComponentScan(basePackages = "com.forsrc")
@EnableAutoConfiguration
@EnableZuulProxy
public class MySpringBootApplication {

    static {
        // for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
            public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                if (hostname.equals("localhost")) {
                    return true;
                }
                return false;
            }
        });
    }

    public static void main(String[] args) {
        try {
            System.setProperty("javax.net.ssl.trustStore",
                    new ClassPathResource("/client.jks").getFile().getAbsolutePath());
            System.setProperty("javax.net.ssl.trustStorePassword", "apache");
            System.setProperty("javax.net.ssl.keyStore",
                    new ClassPathResource("/server.jks").getFile().getAbsolutePath());
            System.setProperty("javax.net.ssl.keyStorePassword", "apache");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpringApplication.run(MySpringBootApplication.class, args);
    }

    @Profile("!cloud")
    // @Bean
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }
}
