package org.example.gatewayapplication;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .ignoringRequestMatchers("/api/auth/login")  // Disable CSRF for the login endpoint
                .and()
                .authorizeRequests()
                .requestMatchers("/api/auth/login").permitAll()  // Allow access to the login endpoint
                .anyRequest().authenticated();
        return http.build();
    }
}
