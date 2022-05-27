package com.cfa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class AppDomain {

  public static void main(String[] args) {
    SpringApplication.run(AppDomain.class, args);
  }
}
