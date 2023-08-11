package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.CategoryApi;
import com.PersonalProject.Jemo.dto.CategoryDto;
import com.PersonalProject.Jemo.services.CategoryService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController implements CategoryApi {

    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @Override
    public CategoryDto findCategoryByNameCategory(String categoryName) {
        return categoryService.findCategoryByNameCategory(categoryName);
    }

    @Override
    public CategoryDto findById(Long id) {
        return categoryService.findById(id);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    public void delete(Long id) {
         categoryService.delete(id);
    }
}
