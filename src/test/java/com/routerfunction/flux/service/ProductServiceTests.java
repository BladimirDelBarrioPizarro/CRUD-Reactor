package com.routerfunction.flux.service;

import com.routerfunction.flux.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void loginTest(){

        webTestClient.post()
                .uri("/login")
                .body(BodyInserters.fromFormData("username","admin").with("password","admin@admin.com"))
                .exchange()
                .expectStatus().isFound();

    }

    @Test
    public void findAllTests(){
        webTestClient.get()
                .uri("/api/v1/products")
                .exchange()
                .expectStatus().isFound()
                .expectBodyList(Product.class)
                .consumeWith( item -> {
                    if(Objects.requireNonNull(item.getResponseBody()).size()>0){
                        item.getResponseBody().forEach(product -> log.info(product.getFile()));
                    }
                    else{
                        log.info("Response Empty");
                    }
                    assert (item.getResponseBody().size()==0);
                });

    }

    @Test
    public void saveTest(){
        Product product = new Product("item test",7400,"test.jpg");
        webTestClient.post()
                .uri("/api/v1/products")
                .body(Mono.just(product),Product.class)
                .exchange()
                .expectStatus().isFound()
                .expectBody();
    }
}
