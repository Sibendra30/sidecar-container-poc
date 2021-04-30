package com.wp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@ComponentScan("com.wp.producer")
public class TokenSchedularMainApp {

        public static void main(String[] args) {
            SpringApplication.run(TokenSchedularMainApp.class, args);
        }
}
