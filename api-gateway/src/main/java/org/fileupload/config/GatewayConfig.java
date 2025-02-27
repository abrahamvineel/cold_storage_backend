package org.fileupload.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("file-upload-service", r -> r.path("/files/**")
                        .uri("http://localhost:9194"))
                .route("user-service", r -> r.path("/user/**")
                        .uri("http://localhost:9193"))
                .build();
    }
}
