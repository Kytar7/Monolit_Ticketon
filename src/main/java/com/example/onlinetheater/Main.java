package com.example.onlinetheater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.onlinetheater.model")
@EnableJpaRepositories("com.example.onlinetheater.repository")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
