package com.api.renascer.infra.security;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@EnableAutoConfiguration
public class CorsConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/**")
           .allowedOrigins("http://localhost:4200, https://igrejarenascer.org.br, https://renascer-app-tiago-barbosas-projects.vercel.app")
           .allowedMethods("*")
           .allowedHeaders("*")
           .allowCredentials(true)
           .maxAge(3600);
   }
}
