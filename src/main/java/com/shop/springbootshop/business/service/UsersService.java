package com.shop.springbootshop.business.service;


import com.shop.springbootshop.DTOmodel.UsersDTO;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<UsersDTO> getAllUsers();

    Optional<UsersDTO> getById(String id);

    UsersDTO saveUser(UsersDTO usersDTO);

    void updateUser(String id, UsersDTO usersDTO);

    void deleteById(String id);
}
