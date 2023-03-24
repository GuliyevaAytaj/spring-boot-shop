package com.shop.springbootshop.business.service.impl;

import com.shop.springbootshop.DTOmodel.UsersDTO;
import com.shop.springbootshop.business.mapper.UsersMapping;
import com.shop.springbootshop.business.repository.UsersRepository;
import com.shop.springbootshop.business.repository.model.UsersEntity;
import com.shop.springbootshop.business.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UsersServiceImpl
        implements UsersService {
    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    private final UsersMapping mapper;

    public UsersServiceImpl(UsersRepository usersRepository, UsersMapping mapper) {
        this.usersRepository = usersRepository;
        this.mapper = mapper;

    }

    @Override
    public List<UsersDTO> getAllUsers() {
        List<UsersEntity> usersEntityList;
        usersEntityList = usersRepository.findAll();
        return usersEntityList.stream().map(mapper::usersEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<UsersDTO> getById(String id) {
        Optional<UsersEntity> usersEntity = usersRepository.findById(id);
        return usersEntity.map(mapper::usersEntityToDto);

    }

    @Override
    public UsersDTO saveUser(UsersDTO usersDTO) {

        UsersEntity usersEntity = mapper.usersDtoToEntity(usersDTO);
        usersEntity.setCreatedDate(Date.from(Instant.now()));
        UsersEntity saveUser = usersRepository.save(usersEntity);
        return mapper.usersEntityToDto(saveUser);
    }


    @Override
    public void updateUser(String id, UsersDTO usersDTO) {


        Optional<UsersEntity> updatedUser = usersRepository.findById(id);
        updatedUser.ifPresent(u -> {
            u.setAddress(usersDTO.getAddress());
            u.setEmail(usersDTO.getEmail());
            u.setName(usersDTO.getName());
            u.setNumber(usersDTO.getNumber());
            u.setSurname(usersDTO.getSurname());
            u.setOrders(usersDTO.getOrders());
            u.setPassword(usersDTO.getPassword());
            usersRepository.save(u);
        });
    }

    @Override
    public void deleteById(String id) {
        usersRepository.deleteById(id);
    }


}
