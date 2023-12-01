package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findCategoryByNameCategory(String categoryName);
}
