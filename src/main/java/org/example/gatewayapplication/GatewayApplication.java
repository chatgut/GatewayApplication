package org.example.gatewayapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;


@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    RouteLocator gateway(RouteLocatorBuilder rlb) {
        return rlb
                .routes()
                .route(p -> p
                        .path("/auth/login/**")
                        .filters(f -> f
                                .tokenRelay()
                                .rewritePath("/auth/login/(?<segment>.*)", "/api/auth/login/$\\{segment}"))
                        .uri("http://localhost:8003"))
                .route(p -> p
                        .path("/posts/**")
                        .filters(f -> f.rewritePath("/posts/(?<segment>.*)", "/posts/$\\{segment}"))
                        .uri("http://localhost:8000"))
                .route(p -> p
                        .path("/images/**")
                        .filters(f -> f.rewritePath("/images/(?<segment>.*)", "/images/$\\{segment}"))
                        .uri("http://localhost:8001"))
                .route(p -> p
                        .path("/users/**")
                        .filters(f -> f.rewritePath("/users/(?<segment>.*)", "/users/$\\{segment}"))
                        .uri("http://localhost:8002"))

                .route(p -> p
                        .path("/like/**")
                        .filters(f -> f.rewritePath("/like/(?<segment>.*)", "/like/$\\{segment}"))
                        .uri("http://localhost:8005"))
                .build();
    }


}




