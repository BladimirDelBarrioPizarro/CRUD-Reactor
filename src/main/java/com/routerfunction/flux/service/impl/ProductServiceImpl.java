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
        return productDao.save(product)
                .doOnNext(item -> log.info(" -- POST /products/{}",product.getId()));
    }

    @Override
    public Mono<Product> update(Product product) {
        return productDao.save(product)
                .doOnNext(item -> log.info(" -- PUT /products/{}",product.getId()));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return productDao.deleteById(id)
                .doOnNext(item -> log.info(" -- DELETE /product/{}",id));
    }
}
