package com.shop.springbootshop.webController;

import com.shop.springbootshop.DTOmodel.CouponDTO;
import com.shop.springbootshop.business.service.CouponService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {
    @Autowired
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/admin/find-all")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<List<CouponDTO>> getAllProducts() {
        List<CouponDTO> products = (couponService.getAllCoupons());
        return
                ResponseEntity.ok(products);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/any/find-by-id")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<Optional<CouponDTO>> getById(String id) {
        Optional<CouponDTO> coupon = (couponService.getById(id));
        return
                ResponseEntity.ok(coupon);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/admin/create-coupon")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CouponDTO> createProduct(@RequestBody CouponDTO couponDTO) {

        CouponDTO createCoupon = couponService.createCoupon(couponDTO);
        return ResponseEntity.ok(createCoupon);

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/admin/coupon-update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateById(@RequestParam String id, @RequestBody CouponDTO couponDTO) {
        couponService.updateCoupon(id, couponDTO);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/admin/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteCoupon(String id) {
        Optional<CouponDTO> coupon = couponService.getById(id);
        if (coupon.isPresent()) {
            couponService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
