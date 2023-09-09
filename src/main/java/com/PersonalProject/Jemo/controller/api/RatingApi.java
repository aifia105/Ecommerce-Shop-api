package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.RatingDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.PersonalProject.Jemo.utils.Constants.RATING_ENDPOINT;

@Tag(name = "Rating")
public interface RatingApi {

    @PostMapping(value =RATING_ENDPOINT + "/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<RatingDto> save(@RequestBody RatingDto ratingDto);

    @GetMapping(value =RATING_ENDPOINT + "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RatingDto> findById(@PathVariable Long id);

    @GetMapping(value =RATING_ENDPOINT + "/All",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<RatingDto>> findAll();

    @GetMapping(value =RATING_ENDPOINT + "/filter/user/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<RatingDto>> findAllByUserId(@PathVariable Long id);

    @GetMapping(value =RATING_ENDPOINT + "/filter/product/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<RatingDto>> findAllByProductId(@PathVariable Long id);

    @DeleteMapping(value =RATING_ENDPOINT + "/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

    @RequestMapping(value = RATING_ENDPOINT + "/product/averageRating/{id}")
    ResponseEntity<Double> averageRating(@PathVariable Long id);
}
