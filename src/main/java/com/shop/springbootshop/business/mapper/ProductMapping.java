package com.shop.springbootshop.business.mapper;

import com.shop.springbootshop.DTOmodel.ProductDTO;
import com.shop.springbootshop.business.repository.model.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapping {
    @Mapping(source = "_id", target = "_id")
    ProductDTO productEntityToDto(ProductEntity productEntity);

    ProductEntity productDtoToEntity(ProductDTO productDTO);
}
