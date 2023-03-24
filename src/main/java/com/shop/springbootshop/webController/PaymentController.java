package com.shop.springbootshop.webController;

import com.shop.springbootshop.DTOmodel.PaymentDTO;
import com.shop.springbootshop.business.service.PaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    @Autowired
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/admin/find-all")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<List<PaymentDTO>> getAllProducts() {
        List<PaymentDTO> payments = (paymentService.getAllPayments());
        return
                ResponseEntity.ok(payments);
    }

    @GetMapping("/any/find-by-id")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<Optional<PaymentDTO>> getById(String id) {
        Optional<PaymentDTO> payment = (paymentService.getById(id));
        return
                ResponseEntity.ok(payment);
    }

    @PostMapping("/any/create-payment")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {

        PaymentDTO createPayment = paymentService.savePayment(paymentDTO);
        return ResponseEntity.ok(createPayment);

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/admin/payment-update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateById(@RequestParam String id, @RequestBody PaymentDTO paymentDTO) {
        paymentService.updatePayment(id, paymentDTO);
    }

    @DeleteMapping("/any/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deletePayment(String id) {
        Optional<PaymentDTO> payment = paymentService.getById(id);
        if (payment.isPresent()) {
            paymentService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
