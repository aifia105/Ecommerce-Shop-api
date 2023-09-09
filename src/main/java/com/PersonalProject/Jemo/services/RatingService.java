package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.RatingDto;

import java.util.List;

public interface RatingService {

    RatingDto save(RatingDto ratingDto);

    RatingDto findById(Long id);

    List<RatingDto> findAll();

    List<RatingDto> findAllByUserId(Long id);

    List<RatingDto> findAllByProductId(Long id);

    void delete(Long id);

    double averageRating(Long id);

}
