package com.example.codereview.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CodeReviewService {

    @Autowired
    private RestTemplate restTemplate;

    public void processPullRequest(String payload) {
        JSONObject prData = new JSONObject(payload);
        String filesUrl = prData.getJSONObject("pull_request").getString("url") + "/files";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("your_github_token"); // Make sure to securely store and use your GitHub token
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(filesUrl, HttpMethod.GET, entity, String.class);

        JSONArray files = new JSONArray(response.getBody());
        for (int i = 0; i < files.length(); i++) {
            JSONObject file = files.getJSONObject(i);
            analyzeFile(file.getString("filename"));
        }
    }

    public void analyzeFile(String filename) {
        // Add your analysis logic here
    }

    public void postCommentToPR(String prUrl, String comment) {
        JSONObject body = new JSONObject();
        body.put("body", comment);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("your_github_token");
        HttpEntity<String> request = new HttpEntity<>(body.toString(), headers);
        restTemplate.postForObject(prUrl + "/comments", request, String.class);
    }
}
