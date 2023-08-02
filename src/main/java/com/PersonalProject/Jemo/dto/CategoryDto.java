package com.PersonalProject.Jemo.dto;


import com.PersonalProject.Jemo.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDto {

    private Integer id;

    private String codeCategory;

    private String nameCategory;

    @JsonIgnore
    private List<ProductDto> products;

    public static CategoryDto fromEntity(Category category) {
        if(category == null){
            return null;
        }
        return CategoryDto.builder()
                .id(category.getId())
                .codeCategory(category.getCodeCategory())
                .nameCategory(category.getNameCategory()).build();

    }

    public static Category toEntity(CategoryDto categoryDto){
        if(categoryDto == null){
            return null;
        }
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCodeCategory(categoryDto.getCodeCategory());
        category.setNameCategory(categoryDto.getNameCategory());
        return category;
}
}
