package com.shop.springbootshop.business.service.impl;

import com.shop.springbootshop.DTOmodel.DeliveryDTO;
import com.shop.springbootshop.business.mapper.DeliveryMapping;
import com.shop.springbootshop.business.repository.DeliveryRepository;
import com.shop.springbootshop.business.repository.model.DeliveryEntity;
import com.shop.springbootshop.business.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private final DeliveryRepository deliveryRepository;

    @Autowired
    private final DeliveryMapping mapper;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, DeliveryMapping mapper) {
        this.deliveryRepository = deliveryRepository;
        this.mapper = mapper;
    }


    @Override
    public List<DeliveryDTO> getAllDeliveries() {
        List<DeliveryEntity> deliveryEntityList;
        deliveryEntityList = deliveryRepository.findAll();

        return deliveryEntityList.stream().map(mapper::deliveryEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<DeliveryDTO> getById(String id) {

        Optional<DeliveryEntity> deliveryEntity = deliveryRepository.findById(id);

        return deliveryEntity.map(mapper::deliveryEntityToDto);

    }


    @Override
    public DeliveryDTO saveDelivery(DeliveryDTO deliveryDTO) {

        DeliveryEntity deliveryEntity = mapper.deliveryDtoToEntity(deliveryDTO);


        DeliveryEntity savePayment = deliveryRepository.save(deliveryEntity);
        return mapper.deliveryEntityToDto(savePayment);
    }


    @Override
    public void updateDelivery(String id, DeliveryDTO deliveryDTO) {

        Optional<DeliveryEntity> updatedDelivery = deliveryRepository.findById(id);
        updatedDelivery.ifPresent(d -> {
            d.setDeliveryAddress(deliveryDTO.getDeliveryAddress());
            d.setOrderedDate(deliveryDTO.getOrderedDate());
            d.setUserId(deliveryDTO.getUserId());


            deliveryRepository.save(d);
        });
    }

    @Override
    public void deleteById(String id) {
        deliveryRepository.deleteById(id);
    }
}
