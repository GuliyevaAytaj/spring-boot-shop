package com.shop.springbootshop.business.service;

import com.shop.springbootshop.DTOmodel.DeliveryDTO;

import java.util.List;
import java.util.Optional;

public interface DeliveryService {
    List<DeliveryDTO> getAllDeliveries();

    Optional<DeliveryDTO> getById(String id);

    DeliveryDTO saveDelivery(DeliveryDTO deliveryDTO);

    void updateDelivery(String id, DeliveryDTO deliveryDTO);

    void deleteById(String id);
}
