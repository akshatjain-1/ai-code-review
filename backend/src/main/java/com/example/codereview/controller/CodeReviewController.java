package com.example.codereview.controller;

import com.example.codereview.model.CodeReviewRequest;
import com.example.codereview.model.CodeReviewResponse;
import com.example.codereview.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/review")
public class CodeReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public CodeReviewResponse reviewCode(@RequestBody CodeReviewRequest request) {
        return reviewService.processReview(request);
    }
}