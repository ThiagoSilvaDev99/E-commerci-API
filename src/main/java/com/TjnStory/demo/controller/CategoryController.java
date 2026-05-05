package com.TjnStory.demo.controller;

import com.TjnStory.demo.dto.*;
import com.TjnStory.demo.service.CategoryService;
import com.TjnStory.demo.controller.docs.CategoryControllerDocs;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")

public class CategoryController implements CategoryControllerDocs {

    private final CategoryService categoryService;

    @Override
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO>  getCategoryById(@PathVariable UUID id){
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody @Valid CategoryCreateDTO dto, UriComponentsBuilder uriBuilder){
        CategoryResponseDTO newCategory = categoryService.createCategory(dto);

        URI location = uriBuilder.path("/categories/{id}")
                .buildAndExpand(newCategory.id())
                .toUri();

        return ResponseEntity.created(location).body(newCategory);
    }

    @Override
    @PatchMapping("/{id}/name")
    public ResponseEntity<CategoryResponseDTO> changeName(@PathVariable UUID id, @RequestBody @Valid CategoryUpdateNameDTO dto){
        CategoryResponseDTO updatedCategory = categoryService.updateName(id, dto);
        return ResponseEntity.ok(updatedCategory);
    }

    @Override
    @PatchMapping("/{id}/parent")
    public ResponseEntity<CategoryResponseDTO> changeParent(@PathVariable UUID id, @RequestBody CategoryUpdateParentDTO dto){
        CategoryResponseDTO updatedCategory = categoryService.changeParent(id, dto);
        return ResponseEntity.ok(updatedCategory);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
