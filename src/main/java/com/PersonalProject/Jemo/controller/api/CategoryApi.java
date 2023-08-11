package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.CategoryDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.PersonalProject.Jemo.utils.Constants.APP_ROOT;

public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/Category/create" , produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto save(@RequestBody CategoryDto categoryDto);

    @GetMapping(value = APP_ROOT + "/Category/{categoryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findCategoryByNameCategory(@PathVariable String categoryName);

    @GetMapping(value = APP_ROOT + "/Category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findById(@PathVariable Long id);

    @GetMapping(value = APP_ROOT + "/Category/All", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/Category/delete/{id}")
    void delete(@PathVariable Long id);

}
