package com.TjnStory.demo.product.mapper;

import com.TjnStory.demo.category.dto.CategoryResponseDTO;
import com.TjnStory.demo.category.entity.Category;
import com.TjnStory.demo.product.dto.ProductCreateDTO;
import com.TjnStory.demo.product.dto.ProductResponseDTO;
import com.TjnStory.demo.product.entity.Product;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductMapper {

    public Product convertToEntity(ProductCreateDTO dto, Category category) {

        return Product.create(dto.name(), category, dto.costPrice(), dto.price(), dto.stockQuantity());
    }

    public ProductResponseDTO convertToDTO(Product product) {

        UUID parentId = null;
        if (product.getCategory().getParent() != null) {
            parentId = product.getCategory().getParent().getId();
        }

        CategoryResponseDTO categoryDTO = new CategoryResponseDTO(
                product.getCategory().getId(),
                product.getCategory().getName(),
                parentId
        );

        return new ProductResponseDTO(product.getId(), product.getName(), product.getPrice(), product.getStockQuantity(), categoryDTO);
    }
}
