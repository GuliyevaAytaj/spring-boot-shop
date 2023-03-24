package com.shop.springbootshop.webController;

import com.shop.springbootshop.DTOmodel.ProductDTO;
import com.shop.springbootshop.business.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/any/find-all")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = (productService.getAllProducts());
        return
                ResponseEntity.ok(products);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/any/find-by-id")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<Optional<ProductDTO>> getById(String id) {
        Optional<ProductDTO> product = (productService.getById(id));
        return
                ResponseEntity.ok(product);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/admin/create-product")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {

        ProductDTO createProduct = productService.saveProduct(productDTO);
        return ResponseEntity.ok(createProduct);

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/admin/product-update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateById(@RequestParam String id, @RequestBody ProductDTO productDTO) {
        productService.updateProduct(id, productDTO);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/admin/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteProduct(String id) {
        Optional<ProductDTO> product = productService.getById(id);
        if (product.isPresent()) {
            productService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
