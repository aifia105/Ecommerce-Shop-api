package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.CategoryDto;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.exception.OperationNotValidException;
import com.PersonalProject.Jemo.model.Product;
import com.PersonalProject.Jemo.repository.CategoryRepository;
import com.PersonalProject.Jemo.repository.ProductRepository;
import com.PersonalProject.Jemo.services.CategoryService;
import com.PersonalProject.Jemo.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        super();
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        List<String> errors = CategoryValidator.validator(categoryDto);
        if (!errors.isEmpty()){
            log.error("Category invalid {}",categoryDto);
            throw new EntityNotValidException("Category invalid", ErrorCodes.CATEGORY_NOT_VALID,errors);
        }
        return CategoryDto.fromEntity(
                categoryRepository.save(
                        CategoryDto.toEntity(categoryDto)));
    }

    @Override
    public CategoryDto findCategoryByNameCategory(String categoryName) {
        if(!StringUtils.hasLength(categoryName)){
            log.error("Category name is null");
            return null;
        }
        return categoryRepository.findCategoryByNameCategory(categoryName)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() ->
                        new EntityNotFoundException("No category with the name " + categoryName
                        ,ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public CategoryDto findById(Long id) {
        if(id == null){
            log.error("ID is null");
            return null;
        }
        return categoryRepository.findById(id)
                .map(CategoryDto::fromEntity).
                orElseThrow(() ->
                        new EntityNotFoundException("No category with the id " + id
                                ,ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream().map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null){
            log.error("ID is null");
            return;
        }
        List<Product> products = productRepository.findAllByCategoryId(id);
        if(!products.isEmpty()){
            throw new OperationNotValidException("Category already in use",ErrorCodes.CATEGORY_ALREADY_IN_USE);
        }
            categoryRepository.findById(id);


    }
}
