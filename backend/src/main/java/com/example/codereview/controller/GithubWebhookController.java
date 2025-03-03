package com.example.codereview.controller;

import com.example.codereview.service.CodeReviewService;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;



@RestController
public class GitHubWebhookController {

    private static final Logger logger = LoggerFactory.getLogger(GitHubWebhookController.class);


    @Autowired
    private CodeReviewService codeReviewService;

    @PostMapping("/webhook")
    public ResponseEntity<String> handleGitHubWebhook(@RequestBody String payload, @RequestHeader("X-GitHub-Event") String githubEvent) {

        logger.info("Webhook event received: {}", githubEvent);
        logger.info("Payload: {}", payload);

        if ("pull_request".equals(githubEvent)) {
            JSONObject jsonPayload = new JSONObject(payload);
            if ("opened".equals(jsonPayload.getString("action")) || "synchronize".equals(jsonPayload.getString("action"))) {
                processPullRequest(jsonPayload.getJSONObject("pull_request"));
            }
        }
        return ResponseEntity.ok("Received");
    }

    private void processPullRequest(JSONObject pullRequest) {
        String filesUrl = pullRequest.getString("url") + "/files";
        // Assuming filesUrl and authentication are correctly set up
        // Fetch the files and call analyzeFile for each
        
        codeReviewService.analyzeFile("okok.java"); // Example call
    }
}
