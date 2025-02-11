package com.ms.rest.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
public class RestMicroserviceBaseApplication {

    public static void main(String[] args) {
        byte[] key = new byte[32]; // 32 bytes = 256 bits
        new SecureRandom().nextBytes(key);
        String base64Key = Base64.getEncoder().encodeToString(key);
        System.out.println("Generated Secret Key: " + base64Key);


        SpringApplication.run(RestMicroserviceBaseApplication.class, args);
    }

}
