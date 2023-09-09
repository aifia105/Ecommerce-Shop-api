package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating , Long> {

    List<Rating> findAllByCustomerId(Long id);

    List<Rating> findAllByProductId(Long id);
}
