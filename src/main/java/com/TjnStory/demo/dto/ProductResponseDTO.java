package com.TjnStory.demo.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(

        UUID id,
        String name,
        BigDecimal price,
        Integer stockQuantity,
        CategoryResponseDTO category
) {
}
