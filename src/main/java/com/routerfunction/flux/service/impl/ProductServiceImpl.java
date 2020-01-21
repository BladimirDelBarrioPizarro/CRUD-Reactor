package com.routerfunction.flux.service.impl;

import com.routerfunction.flux.dao.ProductDao;
import com.routerfunction.flux.model.Product;
import com.routerfunction.flux.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;


@Slf4j
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Flux<Product> findAll() {
        return productDao.findAll().flatMap(Mono::just).delayElements(Duration.ofSeconds(3))
                .doOnNext(item -> log.info(" -- GET /products {}",item.getItem()));
    }

    @Override
    public Mono<Product> findById(String id) {
        return productDao.findById(id)
                .doOnNext(item -> log.info(" -- GET /products/{}",id));
    }

    @Override
    public Mono<Product> save(Product product) {
        return productDao.save(product);
    }

    @Override
    public Mono<Void> delete(Product product) {
        return productDao.delete(product);
    }
}
