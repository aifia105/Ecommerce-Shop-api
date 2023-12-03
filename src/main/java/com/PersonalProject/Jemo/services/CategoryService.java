package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto findCategoryByNameCategory(String categoryName);

    CategoryDto findById(Long  id);

    List<CategoryDto> findAll();

    void delete(Long  id);

}
