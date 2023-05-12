package com.productService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.productService.dto.ProductRequest;
import com.productService.dto.ProductResponse;
import com.productService.model.Product;
import com.productService.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;


    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
            .name(productRequest.getName())
            .description(productRequest.getDescription())
            .price(productRequest.getPrice())
            .build();

        productRepository.save(product);

        log.info("Product {} is created", product.getId());

    }

    public List<ProductResponse> getAllProducts(){
        
        List<Product> products = productRepository.findAll();

        List<ProductResponse> productsResponse = products.stream().map(this::mapToProductResponse).toList();

        return productsResponse;
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .build();
    }
}
