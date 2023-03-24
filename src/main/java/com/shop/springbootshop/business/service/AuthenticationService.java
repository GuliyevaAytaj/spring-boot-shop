package com.shop.springbootshop.business.service;

import com.shop.springbootshop.DTOmodel.AuthenticationRequestDTO;
import com.shop.springbootshop.DTOmodel.AuthenticationResponseDTO;
import com.shop.springbootshop.DTOmodel.UsersDTO;

public interface AuthenticationService {

    AuthenticationResponseDTO register(UsersDTO request);

    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);

}
