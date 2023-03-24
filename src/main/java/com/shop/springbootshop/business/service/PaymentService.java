package com.shop.springbootshop.business.service;

import com.shop.springbootshop.DTOmodel.PaymentDTO;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<PaymentDTO> getAllPayments();

    Optional<PaymentDTO> getById(String id);

    PaymentDTO savePayment(PaymentDTO paymentDTO);

    void updatePayment(String id, PaymentDTO paymentDTO);

    void deleteById(String id);
}
