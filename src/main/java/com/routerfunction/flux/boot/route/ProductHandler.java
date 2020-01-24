package com.routerfunction.flux.boot.route;

import com.routerfunction.flux.boot.properties.Properties;
import com.routerfunction.flux.model.Product;
import com.routerfunction.flux.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.FormFieldPart;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.File;
import java.net.URI;
import java.util.UUID;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;


@Component
public class ProductHandler {


    private ProductService productService;

    private Properties properties;

    public ProductHandler(ProductService productService, Properties properties){
        this.properties = properties;
        this.productService = productService;
    }


    public Mono<ServerResponse> getAll(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.findAll(), Product.class);
    }


   public Mono<ServerResponse> getById(ServerRequest request){
       return productService.findById(request.pathVariable("id"))
               .flatMap( product -> ServerResponse
               .ok()
               .contentType(MediaType.APPLICATION_JSON)
               .body(fromValue(product)))
               .switchIfEmpty(ServerResponse.notFound().build());
   }

   public Mono<ServerResponse> insertProduct(ServerRequest request) {
         return request.bodyToMono(Product.class)
                .flatMap(item -> productService.save(item))
                .flatMap(item -> ServerResponse
                                .ok()
                                .body(fromValue("URI Resource: "+request.uri().toString().concat("/" + item.getId()))));
   }

   public Mono<ServerResponse> updateProduct(ServerRequest request){
       return request.bodyToMono(Product.class)
               .flatMap(item -> productService.update(item))
               .flatMap(item -> ServerResponse
                       .ok()
                       .body(fromValue("URI Resource: "+request.uri().toString().concat("/" + item.getId()))))
               .switchIfEmpty(ServerResponse.notFound().build());
   }

    public Mono<ServerResponse> saveProductFile(ServerRequest request){
        Mono<Product> productMono = request.multipartData().map(multipart -> {

            FormFieldPart itemFieldPart = (FormFieldPart) multipart.toSingleValueMap().get("item");
            FormFieldPart qtyFieldPart = (FormFieldPart) multipart.toSingleValueMap().get("qty");

            return new Product(itemFieldPart.value(),Integer.parseInt(qtyFieldPart.value()));

        });
        return request.multipartData().map(item -> item.toSingleValueMap().get("file"))
                .cast(FilePart.class)
                .flatMap(file -> productMono
                        .flatMap(product -> {
                            product.setFile(UUID.randomUUID().toString() + "-" + file.filename()
                                    .replace(" ","-")
                                    .replace(":","")
                                    .replace("\\",""));
                            return file.transferTo(new File("C:/src/flux/upload/"+product.getFile()))
                                    .then(productService.save(product));

                        })).flatMap(p -> ServerResponse.created(URI.create("/api/v1/products/".concat(p.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(p)));
    }


   public Mono<ServerResponse> uploadProduct(ServerRequest request){
        String id = request.pathVariable("id");
        return request.multipartData().map(item -> item.toSingleValueMap().get("file"))
                      .cast(FilePart.class)
                      .flatMap(file -> productService.findById(id)
                              .flatMap(product -> {
                                   product.setFile(UUID.randomUUID().toString() + "-" + file.filename()
                                    .replace(" ","-")
                                    .replace(":","")
                                    .replace("\\",""));
                                 return file.transferTo(new File("C:/src/flux/upload/"+product.getFile()))
                                         .then(productService.save(product));

                      })).flatMap(p -> ServerResponse.created(URI.create("/api/v1/products/".concat(p.getId())))
                         .contentType(MediaType.APPLICATION_JSON)
                         .body(fromValue(p)))
                .switchIfEmpty(ServerResponse.notFound().build());

   }


   public Mono<ServerResponse> deleteProduct(ServerRequest request){
        return productService.findById(request.pathVariable("id"))
                .flatMap(item -> ServerResponse
                            .noContent()
                            .build(productService.deleteById(item.getId())))
                .switchIfEmpty(ServerResponse.notFound().build());
   }
}

