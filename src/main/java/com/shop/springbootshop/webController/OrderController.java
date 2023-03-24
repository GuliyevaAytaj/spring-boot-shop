package com.shop.springbootshop.webController;

import com.shop.springbootshop.DTOmodel.OrderDTO;
import com.shop.springbootshop.business.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {


    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/admin/find-all")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> products = (orderService.getAllOrders());
        return
                ResponseEntity.ok(products);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/any/find-by-id")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<Optional<OrderDTO>> getById(String id) {
        Optional<OrderDTO> order = (orderService.getById(id));
        return
                ResponseEntity.ok(order);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/any/create-order")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {

        OrderDTO createOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.ok(createOrder);

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/any/order-update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateById(@RequestParam String id, @RequestBody OrderDTO orderDTO) {
        orderService.updateOrder(id, orderDTO);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/any/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteOrder(String id) {
        Optional<OrderDTO> order = orderService.getById(id);
        if (order.isPresent()) {
            orderService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
