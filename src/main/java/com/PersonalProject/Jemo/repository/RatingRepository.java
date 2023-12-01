package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating , String> {

    List<Rating> findAllByUserId(String id);

    List<Rating> findAllByProductId(String id);
}
