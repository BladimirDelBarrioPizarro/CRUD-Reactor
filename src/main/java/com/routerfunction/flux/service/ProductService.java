package com.routerfunction.flux.service;

import com.routerfunction.flux.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

     Flux<Product> findAll();
     Mono<Product> findById(String id);
     Mono<Product> save(Product product);
     Mono<Product> update(Product product);
     Mono<Void> delete(Product product);
}
