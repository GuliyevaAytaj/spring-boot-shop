package com.shop.springbootshop.business.service;

import com.shop.springbootshop.DTOmodel.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDTO> getAllOrders();

    Optional<OrderDTO> getById(String id);

    OrderDTO createOrder(OrderDTO orderDTO);

    void updateOrder(String id, OrderDTO orderDTO);

    void deleteById(String id);
}
