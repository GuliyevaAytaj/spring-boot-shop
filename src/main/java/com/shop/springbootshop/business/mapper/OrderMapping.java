package com.shop.springbootshop.business.mapper;

import com.shop.springbootshop.DTOmodel.OrderDTO;
import com.shop.springbootshop.business.repository.model.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapping {
    @Mapping(source = "_id", target = "_id")
    OrderDTO orderEntityToDto(OrderEntity orderEntity);

    OrderEntity orderDtoToEntity(OrderDTO orderDTO);
}
