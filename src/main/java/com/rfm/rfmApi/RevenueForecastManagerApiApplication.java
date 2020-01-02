package com.rfm.rfmApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
public class RevenueForecastManagerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RevenueForecastManagerApiApplication.class, args);
    }

   /* @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT")
						.allowedOrigins("*");
                *//*.allowedOrigins("http://localhost:8081");*//*
            }
        };
    }*/

}


