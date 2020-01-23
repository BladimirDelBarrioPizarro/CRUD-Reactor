package com.routerfunction.flux.boot.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Properties {
    @Value("${config.path}")
    private String path;
}
