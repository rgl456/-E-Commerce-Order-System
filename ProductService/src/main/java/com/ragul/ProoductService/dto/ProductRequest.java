package com.ragul.ProoductService.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(

        @NotBlank(message = "SKU is required")
        @Size(min = 3, max = 50)
        String sku,

        @NotBlank(message = "Name is required")
        String name,

        String description,

        @NotBlank(message = "Category is required")
        String category,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Price must be greater than 0")
        BigDecimal price

) {
}
