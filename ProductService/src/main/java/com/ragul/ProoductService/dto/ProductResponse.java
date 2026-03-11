package com.ragul.ProoductService.dto;

import com.ragul.ProoductService.model.ProductStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String sku;
    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    private ProductStatus status;
    private LocalDateTime createdAt;

}
