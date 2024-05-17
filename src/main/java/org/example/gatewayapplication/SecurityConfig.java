package org.example.gatewayapplication;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

@Configuration
@EnableWebFluxSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        /*http
                .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();*/


        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for log in
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/login").permitAll()  // Allow access to the login endpoint
                        .anyExchange().authenticated());
        return http.build();

    }}