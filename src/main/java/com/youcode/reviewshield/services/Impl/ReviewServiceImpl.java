package com.youcode.reviewshield.services.Impl;

import com.youcode.reviewshield.models.dto.ReviewDto;
import com.youcode.reviewshield.models.dto.RoleDto;
import com.youcode.reviewshield.models.dto.UserDto;
import com.youcode.reviewshield.models.entities.Review;
import com.youcode.reviewshield.models.entities.Role;
import com.youcode.reviewshield.models.entities.User;
import com.youcode.reviewshield.repositories.ReviewRepository;
import com.youcode.reviewshield.services.ReviewService;
import com.youcode.reviewshield.utils.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private ModelMapper modelMapper;
    private ReviewRepository reviewRepository;

    @Override
    public ReviewDto save(ReviewDto reviewDto) {
        Review review = modelMapper.map(reviewDto, Review.class);
        return modelMapper.map(reviewRepository.save(review), ReviewDto.class);
    }

    @Override
    public List<ReviewDto> getAll() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .toList();
    }

    @Override
    public ReviewDto update(UUID uuid, ReviewDto reviewDto) {
        Review review = modelMapper.map(reviewDto, Review.class);
        if (!reviewRepository.existsById(uuid))
            throw NotFoundException.getNotFoundException(uuid, "review");
        reviewDto.setId(uuid);
        Optional.ofNullable(review.getTitle()).ifPresent(reviewDto::setTitle);
        Optional.ofNullable(review.getMessage()).ifPresent(reviewDto::setMessage);
        Optional.ofNullable(review.getReactions()).ifPresent(reviewDto::setReactions);
        return modelMapper.map(reviewRepository.save(review), ReviewDto.class);
    }

    @Override
    public void delete(UUID uuid) {
        if (!reviewRepository.existsById(uuid))
            throw NotFoundException.getNotFoundException(uuid, "review");
        reviewRepository.deleteById(uuid);
    }

    @Override
    public ReviewDto findByID(UUID uuid) {
        Review review = reviewRepository.findById(uuid)
                .orElseThrow(() -> NotFoundException.getNotFoundException(uuid, "review"));
        return modelMapper.map(review, ReviewDto.class);
    }
}
