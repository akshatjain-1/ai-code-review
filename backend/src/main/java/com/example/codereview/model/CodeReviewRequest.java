package com.example.codereview.model;

public class CodeReviewRequest {
    private String repository;
    private String commitId;
    private String code; // Could be a diff or a code snippet

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
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
