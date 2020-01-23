package com.routerfunction.flux.boot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public MapReactiveUserDetailsService userDetailsRepository() {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("user@user.com")).roles("USER").build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin@admin.com")).roles("USER","ADMIN").build();
        return new MapReactiveUserDetailsService(user, admin);
    }

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception{
        return http.csrf().disable()
                .authorizeExchange()
                .pathMatchers(HttpMethod.GET,"/api/v1/products**").hasAnyRole("USER","ADMIN")
                .pathMatchers(HttpMethod.POST,"/api/v1/products").hasRole("ADMIN")
                .pathMatchers(HttpMethod.POST,"/api/v1/products/upload/**").hasRole("ADMIN")
                .pathMatchers(HttpMethod.PUT,"/api/v1/products").hasRole("ADMIN")
                .pathMatchers(HttpMethod.DELETE,"/api/v1/products").hasRole("ADMIN")
                .anyExchange().authenticated()
                .and().formLogin()
                .and().build();
    }
}
