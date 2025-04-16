package com.jabadoor.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        // so we send requet to http://localhost:8765/get
        // we add the header and params
        // and we get redirect to http://localhost:xxxx/get?myParams=myParamsValue
        return builder
                .routes()
                .route(
                        predicateSpec -> predicateSpec
                                .path("/get")
                                .filters(
                                        f -> f
                                                .addRequestHeader("myHeader", "myValue")
                                                .addRequestParameter("myParams", "myParamsValue")
                                )
                                .uri("http://localhost:8765/")

                )
                .build();
    }
}
