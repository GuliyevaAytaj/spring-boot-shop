package com.shop.springbootshop.business.mapper;


import com.shop.springbootshop.DTOmodel.UsersDTO;
import com.shop.springbootshop.business.repository.model.UsersEntity;
import org.mapstruct.EnumMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UsersMapping {


    @Mapping(source = "_id", target = "_id")
    UsersDTO usersEntityToDto(UsersEntity usersEntity);

    @Mapping(target = "createdDate", ignore = true)

    @EnumMapping(configuration = "ROLE_", nameTransformationStrategy = "prefix")
    UsersEntity usersDtoToEntity(UsersDTO usersDTO);

}
