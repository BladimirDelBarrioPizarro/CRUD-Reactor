package com.routerfunction.flux.controller;

import com.routerfunction.flux.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



public interface ProductController {

   /* @GetMapping(path="/products")
    ResponseEntity<Flux<Product>> findAll();*/



     /*
    @GetMapping(path="/products/{id}")
    ResponseEntity<Mono<ServerResponse>> findById(@PathVariable("id")String id);



    @PostMapping(path = "/products")
    ResponseEntity<EntityModel<Links>> save(@RequestBody Product product);

    @PutMapping(path = "/products")
    ResponseEntity<EntityModel<Mono<Product>>> update(@RequestBody Product product);

    @DeleteMapping(path = "/products/{id}")
    ResponseEntity<EntityModel<Links>> delete(@PathVariable("id") String id);*/
}
