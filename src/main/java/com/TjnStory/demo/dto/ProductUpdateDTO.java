package com.TjnStory.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductUpdateDTO(

        @NotNull(message = "Cost price is required.")
        @Positive(message = "Cost price must be positive.")
        BigDecimal price
) {
}
