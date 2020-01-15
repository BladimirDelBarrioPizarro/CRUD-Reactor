package com.routerfunction.flux.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
public class Product {
    @Id
    private String id;
    private String item;
    private Integer qty;

    public Product(String id, String item, Integer qty) {
        this.id = id;
        this.item = item;
        this.qty = qty;
    }

    public String getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public Integer getQty() {
        return qty;
    }
}
