package com.ragul.ProoductService.mapper;

import com.ragul.ProoductService.dto.ProductResponse;
import com.ragul.ProoductService.model.Product;

public class ProductMapper {

    public static ProductResponse mapToResponse(Product product){
        ProductResponse response = ProductResponse.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .status(product.getStatus())
                .createdAt(product.getCreatedAt())
                .build();
        return response;
    }

}
