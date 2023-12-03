package com.PersonalProject.Jemo.dto;


import com.PersonalProject.Jemo.model.Category;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CategoryDto {

    private Long id;

    private byte[] image;

    private String nameCategory;


    public static CategoryDto fromEntity(Category category) {
        if(category == null){
            return null;
        }
        return CategoryDto.builder()
                .id(category.getId())
                .image(category.getImage())
                .nameCategory(category.getNameCategory()).build();

    }

    public static Category toEntity(CategoryDto categoryDto){
        if(categoryDto == null){
            return null;
        }
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setImage(categoryDto.getImage());
        category.setNameCategory(categoryDto.getNameCategory());
        return category;
}
}
