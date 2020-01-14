package com.routerfunction.flux.service.impl;

import com.routerfunction.flux.dao.ProductDao;
import com.routerfunction.flux.service.ProductService;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }
}
