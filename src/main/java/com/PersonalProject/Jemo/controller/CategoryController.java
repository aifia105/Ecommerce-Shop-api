package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.CategoryApi;
import com.PersonalProject.Jemo.dto.CategoryDto;
import com.PersonalProject.Jemo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController implements CategoryApi {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<CategoryDto> save(CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.save(categoryDto));
    }

    @Override
    public ResponseEntity<CategoryDto> findCategoryByNameCategory(String categoryName) {
        return ResponseEntity.ok(categoryService.findCategoryByNameCategory(categoryName));
    }

    @Override
    public ResponseEntity<CategoryDto> findById(Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @Override
    public ResponseEntity<List<CategoryDto>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
         categoryService.delete(id);
         return ResponseEntity.ok().build();
    }
}
