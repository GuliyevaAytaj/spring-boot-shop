package com.shop.springbootshop.business.service.impl;

import com.shop.springbootshop.DTOmodel.OrderDTO;
import com.shop.springbootshop.business.mapper.OrderMapping;
import com.shop.springbootshop.business.repository.CouponRepository;
import com.shop.springbootshop.business.repository.OrderRepository;
import com.shop.springbootshop.business.repository.ProductRepository;
import com.shop.springbootshop.business.repository.UsersRepository;
import com.shop.springbootshop.business.repository.model.CouponEntity;
import com.shop.springbootshop.business.repository.model.OrderEntity;
import com.shop.springbootshop.business.repository.model.UsersEntity;
import com.shop.springbootshop.business.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final CouponRepository couponRepository;
    @Autowired
    private final OrderMapping mapper;

    public OrderServiceImpl(UsersRepository usersRepository, ProductRepository productRepository, OrderRepository orderRepository, CouponRepository couponRepository, OrderMapping mapper) {
        this.usersRepository = usersRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.couponRepository = couponRepository;
        this.mapper = mapper;

    }


    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderEntity> orderEntityList;
        orderEntityList = orderRepository.findAll();

        return orderEntityList.stream().map(mapper::orderEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDTO> getById(String id) {

        Optional<OrderEntity> orderEntity = orderRepository.findById(id);

        return orderEntity.map(mapper::orderEntityToDto);

    }


    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {

        OrderEntity orderEntity = mapper.orderDtoToEntity(orderDTO);

        BigDecimal subtotal = BigDecimal.ZERO;
        for (String item : orderDTO.getProductId()) {
            Integer productPrice = productRepository.findById(item).get().getPrice();
            subtotal = subtotal.add(BigDecimal.valueOf(productPrice));
        }
        if (orderDTO.getCouponId() != null) {
            Optional<CouponEntity> discount = couponRepository.findById(orderDTO.getCouponId());

            if (discount.isPresent()) {
                BigDecimal discountAmount = subtotal.multiply(BigDecimal.valueOf(discount.get().getDiscountPercentage())).divide(BigDecimal.valueOf(100));
                BigDecimal discountedAmount = subtotal.subtract(discountAmount);
                orderEntity.setTotalPrice(discountedAmount);
                orderRepository.save(orderEntity);

            }
        } else {
            orderEntity.setTotalPrice(subtotal);
        }
        OrderEntity saveOrder = orderRepository.save(orderEntity);
//        usersEntity.setOrders(new String[]{saveOrder.get_id()});
        Optional<UsersEntity> findUser = usersRepository.findById(orderDTO.getUserId());
        if (findUser.isPresent()) {
            List<String> addOrder = new java.util.ArrayList<>(Arrays.stream(findUser.get().getOrders()).toList());
            addOrder.add(orderDTO.get_id());

        }

        return mapper.orderEntityToDto(saveOrder);
    }


    @Override
    public void updateOrder(String id, OrderDTO orderDTO) {
        Optional<OrderEntity> updatedOrder = orderRepository.findById(id);
        updatedOrder.ifPresent(o -> {
            o.setProductId(orderDTO.getProductId());
            o.setOrderDate(orderDTO.getOrderDate());
            o.setDeliveryId(orderDTO.getDeliveryId());
            o.setDeliveryId(orderDTO.getPaymentId());
            orderRepository.save(o);
        });
    }

    @Override
    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }
}
