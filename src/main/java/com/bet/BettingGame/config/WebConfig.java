package com.bet.BettingGame.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Apply CORS to all /api paths
                        .allowedOrigins("*")
                        .allowedOrigins("http://localhost:3000") // Replace with your frontend's URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
                        .allowedHeaders("*");// Allowed headers
//                        .allowCredentials(true); // Allow cookies

            }
        };
    }
}

