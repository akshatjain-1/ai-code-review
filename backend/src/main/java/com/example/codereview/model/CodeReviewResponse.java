package com.example.codereview.model;

import java.util.List;

public class CodeReviewResponse {
    private String repository;
    private String commitId;
    private List<String> suggestions;

    // Getters and setters
    public String getRepository() {
        return repository;
    }
    public void setRepository(String repository) {
        this.repository = repository;
    }
    public String getCommitId() {
        return commitId;
    }
    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }
    public List<String> getSuggestions() {
        return suggestions;
    }
    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }
}
