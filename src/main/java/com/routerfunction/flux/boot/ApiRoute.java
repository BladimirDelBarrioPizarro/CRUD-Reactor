package com.routerfunction.flux.boot;

import com.routerfunction.flux.model.Product;
import com.routerfunction.flux.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;


@Configuration
public class ApiRoute {


    @Bean
    public RouterFunction<ServerResponse> routes(ProductHandler productHandler){
        return  route(GET("/api/v1/products"), productHandler::getAll)
                .and(route(GET("/api/v1/products/{id}"),productHandler::getById));
    }


}
