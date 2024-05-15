package org.example.gatewayapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


@Component
public class JwtAuthenticationFilter implements GatewayFilter {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // endpoint without login
        if (request.getURI().getPath().contains("/auth/login")) {
            return chain.filter(exchange);
        }

        // get token from authservice2
        String authHeader = request.getHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatusCode.valueOf(401));
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        // Call the authservice to validate token
        return webClientBuilder.build()
                .post()
                .uri("http://authservice2:8080/api/auth/validate")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new RuntimeException("Invalid Token")))
                .bodyToMono(Void.class)
                .then(chain.filter(exchange))
                .onErrorResume(e -> {
                    exchange.getResponse().setStatusCode(HttpStatusCode.valueOf(401));
                    return exchange.getResponse().setComplete();
                });
    }
    }


