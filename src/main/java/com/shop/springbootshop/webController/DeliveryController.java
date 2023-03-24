package com.shop.springbootshop.webController;

import com.shop.springbootshop.DTOmodel.DeliveryDTO;
import com.shop.springbootshop.business.service.DeliveryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/delivery")
public class DeliveryController {
    @Autowired
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/admin/find-all")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<List<DeliveryDTO>> getAllDeliveries() {
        List<DeliveryDTO> delivery = (deliveryService.getAllDeliveries());
        return
                ResponseEntity.ok(delivery);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/any/find-by-id")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<Optional<DeliveryDTO>> getById(String id) {
        Optional<DeliveryDTO> delivery = (deliveryService.getById(id));
        return
                ResponseEntity.ok(delivery);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/any/create-delivery")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DeliveryDTO> createDelivery(@RequestBody DeliveryDTO deliveryDTO) {

        DeliveryDTO createDelivery = deliveryService.saveDelivery(deliveryDTO);
        return ResponseEntity.ok(createDelivery);

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/any/delivery-update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateById(@RequestParam String id, @RequestBody DeliveryDTO deliveryDTO) {
        deliveryService.updateDelivery(id, deliveryDTO);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/any/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteDelivery(String id) {
        Optional<DeliveryDTO> product = deliveryService.getById(id);
        if (product.isPresent()) {
            deliveryService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
