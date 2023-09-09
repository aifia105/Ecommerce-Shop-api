package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.RatingApi;
import com.PersonalProject.Jemo.dto.RatingDto;
import com.PersonalProject.Jemo.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RatingController implements RatingApi {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService  ratingService) {
        super();
        this.ratingService = ratingService;
    }

    @Override
    public ResponseEntity<RatingDto> save(RatingDto ratingDto) {
        return ResponseEntity.ok(ratingService.save(ratingDto));
    }

    @Override
    public ResponseEntity<RatingDto> findById(Long id) {
        return ResponseEntity.ok(ratingService.findById(id));
    }

    @Override
    public ResponseEntity<List<RatingDto>> findAll() {
        return ResponseEntity.ok(ratingService.findAll());
    }

    @Override
    public ResponseEntity<List<RatingDto>> findAllByUserId(Long id) {
        return ResponseEntity.ok(ratingService.findAllByUserId(id));
    }

    @Override
    public ResponseEntity<List<RatingDto>> findAllByProductId(Long id) {
        return ResponseEntity.ok(ratingService.findAllByProductId(id));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        ratingService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Double> averageRating(Long id) {
        return ResponseEntity.ok(ratingService.averageRating(id));
    }
}
