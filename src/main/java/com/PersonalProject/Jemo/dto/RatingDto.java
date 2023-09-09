package com.PersonalProject.Jemo.dto;


import com.PersonalProject.Jemo.model.Rating;
import lombok.Builder;
import lombok.Data;


import java.time.Instant;

@Data
@Builder
public class RatingDto {

    private Long id;

    private ProductDto product;

    private CustomerDto customer;

    private int rate;

    private Instant date;

    public static RatingDto fromEntity(Rating rating){
        if (rating == null){
            return null;
        }
        return RatingDto.builder()
                .id(rating.getId())
                .product(ProductDto.formEntity(rating.getProduct()))
                .customer(CustomerDto.fromEntity(rating.getCustomer()))
                .rate(rating.getRate())
                .date(rating.getDate()).build();

    }

    public static Rating toEntity(RatingDto ratingDto){
        if (ratingDto == null){
            return null;
        }
        Rating rating = new Rating();
        rating.setId(ratingDto.getId());
        rating.setProduct(ProductDto.toEntity(ratingDto.getProduct()));
        rating.setCustomer(CustomerDto.toEntity(ratingDto.getCustomer()));
        rating.setRate(ratingDto.getRate());
        rating.setDate(ratingDto.getDate());
        return rating ;
    }
}
