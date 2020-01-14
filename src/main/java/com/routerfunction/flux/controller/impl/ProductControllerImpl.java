package com.routerfunction.flux.controller.impl;

import com.routerfunction.flux.controller.ProductController;
import com.routerfunction.flux.model.Product;
import com.routerfunction.flux.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ProductControllerImpl implements ProductController {

    private ProductService productService;

    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Flux<Product> findAll() {
        return productService.findAll();
    }

    @Override
    public Mono<Product> findById(String id) {
        return productService.findById(id);
    }

    @Override
    public Mono<Product> save(Product product) {
        return productService.save(product);
    }

    @Override
    public Mono<Void> delete(Product product) {
        return productService.delete(product);
    }
}
