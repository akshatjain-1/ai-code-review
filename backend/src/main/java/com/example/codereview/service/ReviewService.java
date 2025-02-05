package com.example.codereview.service;

import com.example.codereview.model.AnalysisResponse;
import com.example.codereview.model.CodeReviewRequest;
import com.example.codereview.model.CodeReviewResponse;
import com.example.codereview.model.CodeReviewLog;
import com.example.codereview.repository.CodeReviewLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class ReviewService {

    private final WebClient webClient;

    @Autowired
    private CodeReviewLogRepository logRepository;

    // You can inject WebClient or create one here
    public ReviewService(WebClient.Builder webClientBuilder) {
        // The URL "http://ai-review-service:8000" is used when running in Kubernetes;
        // for local testing you might use "http://localhost:8000"
        this.webClient = webClientBuilder.baseUrl("http://ai-review-service:8000").build();
    }

    public CodeReviewResponse processReview(CodeReviewRequest request) {
        List<String> aiSuggestions = callAiReviewService(request.getCode());
        // Optionally, add static analysis suggestions here (e.g., run PMD or SonarQube CLI)

        // Merge suggestions (for simplicity, we use only the AI suggestions)
        CodeReviewResponse response = new CodeReviewResponse();
        response.setRepository(request.getRepository());
        response.setCommitId(request.getCommitId());
        response.setSuggestions(aiSuggestions);

        // Log the review in Elasticsearch
        CodeReviewLog log = new CodeReviewLog();
        log.setRepository(request.getRepository());
        log.setCommitId(request.getCommitId());
        log.setSuggestions(aiSuggestions);
        log.setTimestamp(LocalDateTime.now());
        logRepository.save(log);

        return response;
    }

    private List<String> callAiReviewService(String code) {
        try {
            AnalysisResponse aiResponse = this.webClient.post()
                    .uri("/analyze")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(Collections.singletonMap("code", code))
                    .retrieve()
                    .bodyToMono(AnalysisResponse.class)
                    .block();
            return aiResponse != null ? aiResponse.getSuggestions() : Collections.emptyList();
        } catch (Exception ex) {
            // Log error and return an empty suggestion list or fallback message
            return Collections.singletonList("Error during AI analysis: " + ex.getMessage());
        }
    }
}
