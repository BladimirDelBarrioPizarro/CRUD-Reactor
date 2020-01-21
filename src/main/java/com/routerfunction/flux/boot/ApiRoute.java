package com.routerfunction.flux.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;



@Configuration
public class ApiRoute {

    @Bean
    public RouterFunction<ServerResponse> routes(ProductHandler productHandler){
        return  route(GET("/api/v1/products"), productHandler::getAll)
                .and(route(GET("/api/v1/products/{id}"),productHandler::getById))
                .and(route(POST("/api/v1/products"),productHandler::insertProduct))
                .and(route(PUT("/api/v1/products"),productHandler::updateProduct));
    }


}
