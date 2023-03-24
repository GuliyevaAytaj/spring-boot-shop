package com.shop.springbootshop.business.service.impl;

import com.shop.springbootshop.DTOmodel.ProductDTO;
import com.shop.springbootshop.business.mapper.ProductMapping;
import com.shop.springbootshop.business.repository.ProductRepository;
import com.shop.springbootshop.business.repository.model.ProductEntity;
import com.shop.springbootshop.business.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ProductMapping mapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapping mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }


    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductEntity> productEntityList;
        productEntityList = productRepository.findAll();

        return productEntityList.stream().map(mapper::productEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getById(String id) {

        Optional<ProductEntity> productEntity = productRepository.findById(id);

        return productEntity.map(mapper::productEntityToDto);

    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {

        ProductEntity productEntity = mapper.productDtoToEntity(productDTO);


        ProductEntity saveProduct = productRepository.save(productEntity);
        return mapper.productEntityToDto(saveProduct);
    }


    @Override
    public void updateProduct(String id, ProductDTO productDTO) {
        Optional<ProductEntity> updatedProduct = productRepository.findById(id);
        updatedProduct.ifPresent(p -> {
            p.setName(productDTO.getName());
            p.setCategoryId(productDTO.getCategoryId());
            p.setDescription(productDTO.getDescription());
            p.setImage(productDTO.getImage());
            p.setPrice(productDTO.getPrice());
            productRepository.save(p);
        });
    }

    @Override
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }
}
