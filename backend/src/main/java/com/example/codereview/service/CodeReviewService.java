package com.example.codereview.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class CodeReviewService {

    @Autowired
    private RestTemplate restTemplate;

    public void analyzeFile(String filename, String rawUrl) {
        // Placeholder for actual analysis logic
        String analysisResult = "Analysis of " + filename + ": No issues found.";
        postCommentToPR(rawUrl, analysisResult);
    }

    public void postCommentToPR(String prUrl, String comment) {
        String commentsUrl = prUrl + "/comments";
        JSONObject body = new JSONObject();
        body.put("body", comment);
        String token = "your_personal_access_token"; // Ensure to replace with actual token or configure it securely
        restTemplate.postForObject(commentsUrl, body.toString(), String.class);
    }
}
