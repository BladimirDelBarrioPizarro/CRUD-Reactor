package com.routerfunction.flux.controller.impl;

import com.routerfunction.flux.controller.ProductController;
import com.routerfunction.flux.model.Product;
import com.routerfunction.flux.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;


@Slf4j
public class ProductControllerImpl implements ProductController {

    private ProductService productService;

    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }


    @Override
    public ResponseEntity<Flux<Product>> findAll() {
        log.info(" -- GET /products");
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
}
