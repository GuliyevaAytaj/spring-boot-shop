package com.shop.springbootshop.business.mapper;

import com.shop.springbootshop.DTOmodel.CouponDTO;
import com.shop.springbootshop.business.repository.model.CouponEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CouponMapping {
    @Mapping(source = "_id", target = "_id")
    CouponDTO couponEntityToDto(CouponEntity couponEntity);

    CouponEntity couponDtoToEntity(CouponDTO couponDTO);
}
