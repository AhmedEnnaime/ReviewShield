package com.youcode.reviewshield.services.Impl;

import com.youcode.reviewshield.models.dto.ReviewDto;
import com.youcode.reviewshield.models.dto.RoleDto;
import com.youcode.reviewshield.models.dto.UserDto;
import com.youcode.reviewshield.models.entities.Review;
import com.youcode.reviewshield.models.entities.Role;
import com.youcode.reviewshield.models.entities.User;
import com.youcode.reviewshield.repositories.ReviewRepository;
import com.youcode.reviewshield.repositories.UserRepository;
import com.youcode.reviewshield.services.ReviewService;
import com.youcode.reviewshield.utils.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private ModelMapper modelMapper;
    private ReviewRepository reviewRepository;
    private UserRepository userRepository;

    @Override
    public ReviewDto save(ReviewDto reviewDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(authentication.getName());
        user.ifPresent(reviewDto::setUser);

        reviewDto.setId(UUID.randomUUID());
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
        Optional<Review> optionalReview = reviewRepository.findById(uuid);

        if (optionalReview.isEmpty()) {
            throw NotFoundException.getNotFoundException(uuid, "review");
        }

        Review existingReview = optionalReview.get();
        existingReview.setTitle(reviewDto.getTitle());
        existingReview.setMessage(reviewDto.getMessage());
        existingReview.setReactions(reviewDto.getReactions());
        Review updatedReview = reviewRepository.save(existingReview);
        return modelMapper.map(updatedReview, ReviewDto.class);
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

    @Override
    public void report(UUID uuid) {
        Review existingReview = reviewRepository.findById(uuid)
                .orElseThrow(() -> NotFoundException.getNotFoundException(uuid, "review"));

        if (!existingReview.getReported()) {
            existingReview.setReported(true);
            reviewRepository.save(existingReview);
        }
    }
}
