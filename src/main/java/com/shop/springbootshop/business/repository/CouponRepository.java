package com.shop.springbootshop.business.repository;

import com.shop.springbootshop.business.repository.model.CouponEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CouponRepository extends MongoRepository<CouponEntity, String> {
}
