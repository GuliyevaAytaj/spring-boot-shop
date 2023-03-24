package com.shop.springbootshop.business.mapper;

import com.shop.springbootshop.DTOmodel.AuthenticationRequestDTO;
import com.shop.springbootshop.business.repository.model.UsersEntity;
import org.mapstruct.EnumMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthenticationRequestMapping {
   @EnumMapping(configuration = "ROLE_", nameTransformationStrategy = "prefix")
    AuthenticationRequestDTO usersEntityToDto(UsersEntity usersEntity);
    @Mapping(target = "_id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "surname", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "number", ignore = true)
    @Mapping(target = "orders", ignore = true)
    UsersEntity usersDtoToEntity(AuthenticationRequestDTO authenticationRequestDTO);
}
