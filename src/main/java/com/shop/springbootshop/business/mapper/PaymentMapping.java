package com.shop.springbootshop.business.mapper;

import com.shop.springbootshop.DTOmodel.PaymentDTO;
import com.shop.springbootshop.business.repository.model.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface PaymentMapping {
    @Mapping(source = "_id", target = "_id")
    PaymentDTO paymentEntityToDto(PaymentEntity paymentEntity);

    PaymentEntity paymentDtoToEntity(PaymentDTO paymentDTO);

}
