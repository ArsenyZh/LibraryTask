package com.ArsenyZh.gateway.config;

import com.ArsenyZh.gateway.security.AuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Autowired
    AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("book-service-route", r -> r.path("/api/v1/books/**")
                        .filters(f -> f.filter(authenticationFilter).stripPrefix(3))
                        .uri("lb://book-service"))
                .route("library-service-route", r -> r.path("/api/v1/library/**")
                        .filters(f -> f.filter(authenticationFilter).stripPrefix(3))
                        .uri("lb://library-service"))
                .route("auth-service-route", r -> r.path("/auth/**")
                        .filters(f -> f.filter(authenticationFilter).stripPrefix(1))
                        .uri("lb://auth-service"))
                .build();
    }
}