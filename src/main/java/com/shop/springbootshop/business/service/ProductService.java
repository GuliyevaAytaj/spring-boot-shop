package com.shop.springbootshop.business.service;

import com.shop.springbootshop.DTOmodel.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> getAllProducts();

    Optional<ProductDTO> getById(String id);

    ProductDTO saveProduct(ProductDTO productDTO);


    void updateProduct(String id, ProductDTO productDTO);

    void deleteById(String id);
}
