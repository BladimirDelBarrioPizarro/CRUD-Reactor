package com.routerfunction.flux.service.impl;

import com.routerfunction.flux.dao.ProductDao;
import com.routerfunction.flux.model.Product;
import com.routerfunction.flux.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Flux<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Mono<Product> findById(String id) {
        return productDao.findById(id);
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
