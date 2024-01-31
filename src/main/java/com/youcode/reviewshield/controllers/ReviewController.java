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
            return "pages/add";
        }else{
            reviewService.save(reviewDto);
            return "redirect:/?type=add";
        }
    }
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("review", new ReviewDto());
        return "pages/add";
    }

    @PostMapping("/delete/{id}")
    public String deleteReview(@PathVariable UUID id) {
        reviewService.delete(id);
        return "redirect:/?type=delete";
    }
    
}
