package com.routerfunction.flux.boot;

import com.routerfunction.flux.controller.ProductController;
import com.routerfunction.flux.controller.impl.ProductControllerImpl;
import com.routerfunction.flux.dao.ProductDao;
import com.routerfunction.flux.service.ProductService;
import com.routerfunction.flux.service.impl.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiConfig {

    @Bean
    public ProductService productService(ProductDao productDao){
        return new ProductServiceImpl(productDao);
    }

    @Bean
    public ProductController productController(ProductService productService){
        return new ProductControllerImpl(productService);
    }


}


