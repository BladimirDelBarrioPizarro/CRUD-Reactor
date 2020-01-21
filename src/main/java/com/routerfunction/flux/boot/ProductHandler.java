package com.routerfunction.flux.boot;

import com.routerfunction.flux.model.Product;
import com.routerfunction.flux.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;


@Component
public class ProductHandler {


    @Autowired
    private ProductService productService;


    public Mono<ServerResponse> getAll(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.findAll(), Product.class);
    }


   public Mono<ServerResponse> getById(ServerRequest request){
       return productService.findById(request.pathVariable("id")).flatMap( product -> ServerResponse
               .ok()
               .contentType(MediaType.APPLICATION_JSON)
               .body(fromValue(product)))
               .switchIfEmpty(ServerResponse.notFound().build());
   }

   public Mono<ServerResponse> insertProduct(ServerRequest request) {
        return request.bodyToMono(Product.class)
                .flatMap(item -> productService.save(item))
                .flatMap(item -> ServerResponse
                                .created(URI.create("/api/v1/products".concat(item.getId())))
                                .body(fromValue(item)));
   }
}

