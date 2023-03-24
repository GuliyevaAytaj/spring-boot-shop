package com.shop.springbootshop.business.mapper;

import com.shop.springbootshop.DTOmodel.DeliveryDTO;
import com.shop.springbootshop.business.repository.model.DeliveryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeliveryMapping {
    @Mapping(source = "_id", target = "_id")
    DeliveryDTO deliveryEntityToDto(DeliveryEntity deliveryEntity);

    DeliveryEntity deliveryDtoToEntity(DeliveryDTO deliveryDTO);

}
