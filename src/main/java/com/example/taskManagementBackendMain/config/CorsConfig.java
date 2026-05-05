package com.example.taskManagementBackendMain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        // Allowed Frontend URLs
        configuration.setAllowedOrigins(
                List.of(
                        "http://localhost:5173",
                        "https://task-manager-frontend-red-rho.vercel.app",
                        "https://task-manager-frontend-7ru083i7l-meetakshijaiswal-6575s-projects.vercel.app"
                )
        );

        // Allowed HTTP Methods
        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "PATCH",
                        "OPTIONS"
                )
        );

        // Allowed Headers
        configuration.setAllowedHeaders(
                List.of("*")
        );

        // Allow JWT/Auth Cookies
        configuration.setAllowCredentials(true);

        // Expose Headers if needed
        configuration.setExposedHeaders(
                List.of("Authorization")
        );

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}