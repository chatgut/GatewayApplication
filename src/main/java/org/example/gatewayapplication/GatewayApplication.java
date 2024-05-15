package org.example.gatewayapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;


@SpringBootApplication
public class GatewayApplication  {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }


    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder rlb, JwtAuthenticationFilter jwtAuthenticationFilter) {
        return rlb
                .routes()
                .route(p -> p
                        .path("/auth/login/**")
                        .filters(f -> f.tokenRelay()
                                .rewritePath("/auth/login/(?<segment>.*)", "/api/auth/login/${segment}"))
                        .uri("http://authservice2:8080"))
                .route(p -> p
                        .path("/posts/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter)
                                .rewritePath("/posts/(?<segment>.*)", "/posts/${segment}"))
                        .uri("http://micropostservices:8000"))
                .route(p -> p
                        .path("/images/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter)
                                .rewritePath("/images/(?<segment>.*)", "/images/${segment}"))
                        .uri("http://imageservice:8001"))
                .route(p -> p
                        .path("/users/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter)
                                .rewritePath("/users/(?<segment>.*)", "/users/${segment}"))
                        .uri("http://userservice:8002"))
                .route(p -> p
                        .path("/like/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter)
                                .rewritePath("/like/(?<segment>.*)", "/like/${segment}"))
                        .uri("http://worthreadingservice:8005"))
                .route(p -> p
                        .path("/shorten/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter)
                                .rewritePath("/shorten/(?<segment>.*)", "/shorten/${segment}"))
                        .uri("http://micro-url-shortener-service:8080"))
                .build();
    }


}




