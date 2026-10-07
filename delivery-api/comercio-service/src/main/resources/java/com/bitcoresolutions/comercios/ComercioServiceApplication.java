package com.bitcoresolutions.comercios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ComercioServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComercioServiceApplication.class, args);
    }
}