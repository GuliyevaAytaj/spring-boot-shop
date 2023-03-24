package com.shop.springbootshop.business.service.impl;

import com.shop.springbootshop.DTOmodel.AuthenticationRequestDTO;
import com.shop.springbootshop.DTOmodel.AuthenticationResponseDTO;
import com.shop.springbootshop.DTOmodel.UsersDTO;
import com.shop.springbootshop.business.mapper.AuthenticationRequestMapping;
import com.shop.springbootshop.business.mapper.UsersMapping;
import com.shop.springbootshop.business.repository.UsersRepository;
import com.shop.springbootshop.business.repository.model.UsersEntity;
import com.shop.springbootshop.business.service.AuthenticationService;
import com.shop.springbootshop.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsersRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final UsersMapping usersMapping;
    private final AuthenticationRequestMapping authMapping;


    @Override
    public AuthenticationResponseDTO register(UsersDTO request) {
        var userEntity = usersMapping.usersDtoToEntity(request);
        var user =
                UsersEntity.builder()
                        .name(userEntity.getName())
                        .surname(userEntity.getSurname())
                        .email(userEntity.getEmail())
                        .password(passwordEncoder.encode(userEntity.getPassword()))
                        .role(userEntity.getRole())
//                        .role(Role.ROLE_ADMIN)
                        .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        var register = authMapping.usersDtoToEntity(request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        register.getEmail(),
                        register.getPassword()
                )
        );
        var user = repository.findByEmail(register.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

}
