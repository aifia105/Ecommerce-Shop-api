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

    private UserDto userDto;

    private int rate;

    private Instant date;

    private String message;

    public static RatingDto fromEntity(Rating rating){
        if (rating == null){
            return null;
        }
        return RatingDto.builder()
                .id(rating.getId())
                .product(ProductDto.formEntity(rating.getProduct()))
                .userDto(UserDto.fromEntity(rating.getUser()))
                .rate(rating.getRate())
                .date(rating.getDate())
                .message(rating.getMessage()).build();

    }

    public static Rating toEntity(RatingDto ratingDto){
        if (ratingDto == null){
            return null;
        }
        Rating rating = new Rating();
        rating.setId(ratingDto.getId());
        rating.setProduct(ProductDto.toEntity(ratingDto.getProduct()));
        rating.setUser(UserDto.toEntity(ratingDto.getUserDto()));
        rating.setRate(ratingDto.getRate());
        rating.setDate(ratingDto.getDate());
        rating.setMessage(ratingDto.getMessage());
        return rating ;
    }
}
