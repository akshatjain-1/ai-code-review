package com.example.codereview.controller;

import com.example.codereview.service.CodeReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class GitHubWebhookController {

    @Autowired
    private CodeReviewService codeReviewService;

    @PostMapping("/")
    public ResponseEntity<String> handleGitHubWebhook(@RequestBody String payload, @RequestHeader("X-GitHub-Event") String githubEvent) {
        if ("pull_request".equals(githubEvent)) {
            codeReviewService.processPullRequest(payload);
        }
        return ResponseEntity.ok("Received");
    }
}
