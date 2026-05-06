package com.TjnStory.demo.category.mapper;

import com.TjnStory.demo.category.dto.CategoryResponseDTO;
import com.TjnStory.demo.category.entity.Category;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CategoryMapper {

    public Category convertToEntity(String name, Category parent){
        return Category.createCategory(name, parent);
    }

    public CategoryResponseDTO convertToDTO(Category category) {

        UUID parentId = category.getParent() != null ? category.getParent().getId() : null;

        return new CategoryResponseDTO(category.getId(), category.getName(), parentId);
    }
}
