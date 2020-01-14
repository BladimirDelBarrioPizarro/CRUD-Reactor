package com.routerfunction.flux.controller.impl;

import com.routerfunction.flux.controller.ProductController;
import com.routerfunction.flux.service.ProductService;

public class ProductControllerImpl implements ProductController {

    private ProductService productService;

    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }
}
