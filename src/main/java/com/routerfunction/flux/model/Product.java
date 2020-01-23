package com.routerfunction.flux.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Getter
@Setter
@Document("products")
public class Product {
    @Id
    private String id;
    private String item;
    private Integer qty;
    private String file;
}
