package com.shop.springbootshop.business.service.impl;

import com.shop.springbootshop.DTOmodel.CouponDTO;
import com.shop.springbootshop.business.mapper.CouponMapping;
import com.shop.springbootshop.business.repository.CouponRepository;
import com.shop.springbootshop.business.repository.model.CouponEntity;
import com.shop.springbootshop.business.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private final CouponRepository couponRepository;

    @Autowired
    private final CouponMapping mapper;

    public CouponServiceImpl(CouponRepository couponRepository, CouponMapping mapper) {
        this.couponRepository = couponRepository;
        this.mapper = mapper;
    }


    @Override
    public List<CouponDTO> getAllCoupons() {
        List<CouponEntity> couponEntityList;
        couponEntityList = couponRepository.findAll();

        return couponEntityList.stream().map(mapper::couponEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<CouponDTO> getById(String id) {

        Optional<CouponEntity> couponEntity = couponRepository.findById(id);

        return couponEntity.map(mapper::couponEntityToDto);

    }


    @Override
    public CouponDTO createCoupon(CouponDTO couponDTO) {

        CouponEntity coupon = mapper.couponDtoToEntity(couponDTO);


        CouponEntity saveCoupon = couponRepository.save(coupon);

        return mapper.couponEntityToDto(saveCoupon);
    }


    @Override
    public void updateCoupon(String id, CouponDTO couponDTO) {
        Optional<CouponEntity> updatedCoupon = couponRepository.findById(id);
        updatedCoupon.ifPresent(c -> {
            c.setCode(couponDTO.getCode());
            c.setDiscountPercentage(couponDTO.getDiscountPercentage());
            c.setExpirationDate(couponDTO.getExpirationDate());
            couponRepository.save(c);
        });
    }

    @Override
    public void deleteById(String id) {
        couponRepository.deleteById(id);
    }
}
