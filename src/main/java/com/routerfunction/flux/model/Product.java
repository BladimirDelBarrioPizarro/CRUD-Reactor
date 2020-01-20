package com.routerfunction.flux.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Getter
@Document("products")
public class Product {
    @Id
    private String id;
    private String item;
    private Integer qty;
}
