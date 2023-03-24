package com.shop.springbootshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
public class SpringBootShopApplication {

    public static void main(String[] args) {


        SpringApplication.run(SpringBootShopApplication.class, args);
    }

}
