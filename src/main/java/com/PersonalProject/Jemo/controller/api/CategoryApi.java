package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.CategoryDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import static com.PersonalProject.Jemo.utils.Constants.CATEGORY_ENDPOINT;

@Tag(name = "Categories" )
public interface CategoryApi {

    @PostMapping(value = CATEGORY_ENDPOINT + "/create" , produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto) throws IOException, SQLException;

    @GetMapping(value = CATEGORY_ENDPOINT + "/byName/{categoryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> findCategoryByNameCategory(@PathVariable String categoryName);

    @GetMapping(value = CATEGORY_ENDPOINT + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> findById(@PathVariable Long id);

    @GetMapping(value = CATEGORY_ENDPOINT + "/All", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CategoryDto>> findAll();

    @DeleteMapping(value = CATEGORY_ENDPOINT + "/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

}
