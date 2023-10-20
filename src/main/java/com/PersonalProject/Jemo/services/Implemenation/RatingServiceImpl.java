package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.RatingDto;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.model.User;
import com.PersonalProject.Jemo.model.Product;
import com.PersonalProject.Jemo.model.Rating;
import com.PersonalProject.Jemo.repository.UserRepository;
import com.PersonalProject.Jemo.repository.ProductRepository;
import com.PersonalProject.Jemo.repository.RatingRepository;
import com.PersonalProject.Jemo.services.RatingService;
import com.PersonalProject.Jemo.validator.RatingValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository, UserRepository userRepository, ProductRepository productRepository) {
        super();
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public RatingDto save(RatingDto ratingDto) {
        List<String> errors = RatingValidator.validate(ratingDto);
        if (!errors.isEmpty()){
            log.error("Rating invalid");
            throw new EntityNotValidException("rating not valid "+ ratingDto);
        }
        Optional<User> customer = userRepository.findById(ratingDto.getUserDto().getId());
        if (customer.isEmpty()){
            log.warn("Client not found in database");
            throw new EntityNotFoundException("No client with this ID" + ratingDto.getUserDto().getId(), ErrorCodes.USER_NOT_FOUND);
        }
        Optional<Product> product = productRepository.findById(ratingDto.getProduct().getId());
        if (product.isEmpty()){
            log.warn("Product not found in database");
            throw new EntityNotFoundException("No Product with this ID" + ratingDto.getProduct().getId(), ErrorCodes.PRODUCT_NOT_FOUND);
        }

        return RatingDto.fromEntity(ratingRepository.save(RatingDto.toEntity(ratingDto)));
    }

    @Override
    public RatingDto findById(Long id) {
        if (id == null){
            log.error("rating ID is null");
            return null;
        }
        return ratingRepository.findById(id).map(RatingDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("no rating with this Id is the database" + id));
    }

    @Override
    public List<RatingDto> findAll() {
        return ratingRepository.findAll().stream()
                .map(RatingDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<RatingDto> findAllByUserId(Long id) {
        if (id == null){
            log.error("user ID is null");
            return null;
        }
        return ratingRepository.findAllByUserId(id).stream()
                .map(RatingDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<RatingDto> findAllByProductId(Long id) {
        if (id == null){
            log.error("product ID is null");
            return null;
        }
        return ratingRepository.findAllByProductId(id).stream()
                .map(RatingDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null){
            log.error("rating ID is null");
            return;
        }
        ratingRepository.deleteById(id);

    }

    @Override
    public double averageRating(Long id) {
        double sum = 0;
        List<Rating> ratings =  ratingRepository.findAllByProductId(id);
        int nbRating = ratings.size();
        for (Rating rating : ratings){
            sum += rating.getRate();
        }
        return sum/nbRating;
    }
}
