package com.shop.springbootshop.business.service;

import com.shop.springbootshop.DTOmodel.CouponDTO;

import java.util.List;
import java.util.Optional;

public interface CouponService {
    List<CouponDTO> getAllCoupons();

    Optional<CouponDTO> getById(String id);

    CouponDTO createCoupon(CouponDTO couponDTO);

    void updateCoupon(String id, CouponDTO couponDTO);

    void deleteById(String id);
}
