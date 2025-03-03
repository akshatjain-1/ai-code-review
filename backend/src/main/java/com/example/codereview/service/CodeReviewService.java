package com.example.codereview.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;

import org.json.JSONObject;


@Service
public class CodeReviewService {

    @Autowired
    private RestTemplate restTemplate;

    public void processPullRequest(String payload) {
        JSONObject prData = new JSONObject(payload);
        String filesUrl = prData.getJSONObject("pull_request").getString("url") + "/files";
        HttpHeaders headers = new io.netty.handler.codec.http.HttpHeaders();
        headers.setBearerAuth("your_github_token"); // Make sure to securely store and use your GitHub token
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(filesUrl, HttpMethod.GET, entity, String.class);

        JSONArray files = new JSONArray(response.getBody());
        for (int i = 0; i < files.length(); i++) {
            JSONObject file = files.getJSONObject(i);
            analyzeFile(file.getString("filename"));
        }
    }

    private void analyzeFile(String filename) {
        // Add your analysis logic here
    }

    public void postCommentToPR(String prUrl, String comment) {
        String commentsUrl = prUrl + "/comments";
        JSONObject body = new JSONObject();
        body.put("body", comment);
        String token = "ai-code_review-token"; // Ensure to replace with actual token or configure it securely
        restTemplate.postForObject(commentsUrl, body.toString(), String.class);

    }

    public void processPullRequest(String payload) {
        // TODO: Implement pull request processing logic
    }
}
