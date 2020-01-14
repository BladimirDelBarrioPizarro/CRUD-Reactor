package com.routerfunction.flux.controller;

import com.routerfunction.flux.model.Product;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1")
public interface ProductController {

    @GetMapping(path="/products")
    ResponseEntity<Flux<Product>> findAll();

   /* @GetMapping(path="/products/{id}")
    ResponseEntity<EntityModel<Mono<Product>>> findById(@PathVariable("id")String id);

    @PostMapping(path = "/products")
    ResponseEntity<EntityModel<Links>> save(@RequestBody Product product);

    @PutMapping(path = "/products")
    ResponseEntity<EntityModel<Mono<Product>>> update(@RequestBody Product product);

    @DeleteMapping(path = "/products/{id}")
    ResponseEntity<EntityModel<Links>> delete(@PathVariable("id") String id);*/
}
