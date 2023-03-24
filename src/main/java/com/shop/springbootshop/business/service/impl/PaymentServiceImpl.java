package com.shop.springbootshop.business.service.impl;

import com.shop.springbootshop.DTOmodel.PaymentDTO;
import com.shop.springbootshop.business.mapper.PaymentMapping;
import com.shop.springbootshop.business.repository.PaymentRepository;
import com.shop.springbootshop.business.repository.model.PaymentEntity;
import com.shop.springbootshop.business.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private final PaymentRepository paymentRepository;

    @Autowired
    private final PaymentMapping mapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapping mapper) {
        this.paymentRepository = paymentRepository;
        this.mapper = mapper;
    }


    @Override
    public List<PaymentDTO> getAllPayments() {
        List<PaymentEntity> paymentEntityList;
        paymentEntityList = paymentRepository.findAll();

        return paymentEntityList.stream().map(mapper::paymentEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<PaymentDTO> getById(String id) {

        Optional<PaymentEntity> paymentEntity = paymentRepository.findById(id);

        return paymentEntity.map(mapper::paymentEntityToDto);

    }

    @Override
    public PaymentDTO savePayment(PaymentDTO paymentDTO) {

        PaymentEntity paymentEntity = mapper.paymentDtoToEntity(paymentDTO);


        PaymentEntity savePayment = paymentRepository.save(paymentEntity);
        return mapper.paymentEntityToDto(savePayment);
    }


    @Override
    public void updatePayment(String id, PaymentDTO paymentDTO) {
        Optional<PaymentEntity> updatedPayment = paymentRepository.findById(id);
        updatedPayment.ifPresent(p -> {
            p.setPaymentStatus(paymentDTO.getPaymentStatus());
            p.setBillingAddress(paymentDTO.getBillingAddress());
            p.setPaymentDate(paymentDTO.getPaymentDate());
            p.setTotalCost(paymentDTO.getTotalCost());
            p.setUserId(paymentDTO.getUserId());

            paymentRepository.save(p);
        });
    }

    @Override
    public void deleteById(String id) {
        paymentRepository.deleteById(id);
    }
}
