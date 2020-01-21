package com.routerfunction.flux.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;



@Configuration
public class ApiRoute {

    @Bean
    public RouterFunction<ServerResponse> routes(ProductHandler productHandler){
        return  route(GET("/api/v1/products"), productHandler::getAll)
                .and(route(GET("/api/v1/products/{id}"),productHandler::getById))
                .and(route(POST("/api/v1/products"),productHandler::insertProduct));
    }


}
