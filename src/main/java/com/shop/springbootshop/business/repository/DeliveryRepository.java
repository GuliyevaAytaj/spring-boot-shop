package com.shop.springbootshop.business.repository;

import com.shop.springbootshop.business.repository.model.DeliveryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryRepository extends MongoRepository<DeliveryEntity, String> {
}
