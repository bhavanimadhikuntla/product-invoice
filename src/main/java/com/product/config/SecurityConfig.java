package com.product.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
     // 1. Tells Spring Security to use your CorsConfig configurations
        .cors(cors -> {}) 
        
        // 2. Disables CSRF so React POST requests aren't dropped
        .csrf(csrf -> csrf.disable()) 
        
        .authorizeHttpRequests(auth -> auth
            // 3. FIXED: Correct way to allow browser preflight requests
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // 4. Allows access to all other endpoints for testing purposes
            .anyRequest().permitAll() 
        );
        
    return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Allows any React frontend address to access your deployed endpoints
        configuration.setAllowedOrigins(Arrays.asList("*")); 
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // CRITICAL: Explicitly permits standard frontend headers sent by Axios
        configuration.setAllowedHeaders(Arrays.asList("*")); 
        configuration.setExposedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
        