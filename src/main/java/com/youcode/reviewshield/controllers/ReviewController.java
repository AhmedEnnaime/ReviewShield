package com.youcode.reviewshield.controllers;

import com.youcode.reviewshield.models.dto.ReviewDto;
import com.youcode.reviewshield.services.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    @PostMapping("/add")
    public String createReview(@Valid @ModelAttribute ReviewDto reviewDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.out.println("my errors " + bindingResult.getAllErrors());
            return "pages/reviews/add?type=add";
        }
            reviewService.save(reviewDto);
            return "redirect:/reviews";
    }

    @GetMapping
    public String getAllReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAll());
        return "pages/reviews";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("review", new ReviewDto());
        return "pages/add";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable UUID id, Model model) {
        ReviewDto reviewDto = reviewService.findByID(id);
        model.addAttribute("review", reviewDto);
        return "pages/update";
    }

    @PostMapping("/update/{id}")
    public String updateReview(@PathVariable UUID id, @Valid @ModelAttribute ReviewDto updatedReviewDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Update Review Errors: " + bindingResult.getAllErrors());
            return "pages/update";
        }
        reviewService.update(id, updatedReviewDto);
        return "redirect:/reviews";
    }

    @PostMapping("/report/{id}")
    public String report(@PathVariable UUID id) {
        reviewService.report(id);
        return "redirect:/reviews";
    }

    @PostMapping("/delete/{id}")
    public String deleteReview(@PathVariable UUID id) {
        reviewService.delete(id);
        return "redirect:/";
    }
    
}
