package com.routerfunction.flux.dao;

import com.routerfunction.flux.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductDao extends ReactiveMongoRepository<Product,String> {
}
