package com.shop.springbootshop.business.repository;

import com.shop.springbootshop.business.repository.model.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {
}
