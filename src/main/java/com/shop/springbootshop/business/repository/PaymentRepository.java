package com.shop.springbootshop.business.repository;

import com.shop.springbootshop.business.repository.model.PaymentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<PaymentEntity, String> {
}
