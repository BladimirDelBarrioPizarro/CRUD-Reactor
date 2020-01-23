package com.routerfunction.flux.boot.config;


import com.routerfunction.flux.boot.properties.Properties;
import com.routerfunction.flux.boot.route.ProductHandler;
import com.routerfunction.flux.dao.ProductDao;
import com.routerfunction.flux.service.ProductService;
import com.routerfunction.flux.service.impl.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiConfig {

    @Bean
    public Properties properties(){
        return new Properties();
    }


    @Bean
    public ProductService productService(ProductDao productDao){
        return new ProductServiceImpl(productDao);
    }

    @Bean
    public ProductHandler productHandler(ProductService productService, Properties properties){
        return new ProductHandler(productService,properties);
    }

}


