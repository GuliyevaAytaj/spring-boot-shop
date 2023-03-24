package com.shop.springbootshop.business.repository;

import com.shop.springbootshop.business.repository.model.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
}
