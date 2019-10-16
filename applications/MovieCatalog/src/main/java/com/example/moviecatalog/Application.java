package com.example.moviecatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication/*
@EnableEurekaClient*/
public class Application
{

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

   @Bean
    public ServletRegistrationBean registerActionServlet(ActionServlet actionServlet) {
        return new ServletRegistrationBean(actionServlet, "/moviefun/*");
    }

    @Bean
    public RestTemplate returnRestTemplate()
    {
        return new RestTemplate();
    }



}
