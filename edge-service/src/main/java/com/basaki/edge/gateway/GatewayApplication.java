package com.basaki.edge.gateway;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


/**
 * @author yibo
 */
@SpringBootApplication
public class GatewayApplication {

  
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        // @formatter:off
        return builder.routes()
                .route(
                        r -> r.path("/fluent/customer/**")
                             .filters(f -> f.stripPrefix(2)
                                            .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                             .uri("lb://CONSUMER")
                             .order(0)
                             .id("fluent_customer_service")
                )
                .route(r -> r.path("/throttle/customer/**")
                             .filters(f -> f.stripPrefix(2))
                             .uri("lb://CONSUMER")
                             .order(0)
                             .id("throttle_customer_service")
                )
                .build();
        // @formatter:on
    }



}