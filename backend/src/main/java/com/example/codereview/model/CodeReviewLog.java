package com.example.codereview.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(indexName = "code_reviews")
public class CodeReviewLog {

    @Id
    private String id;
    private String repository;
    private String commitId;
    private List<String> suggestions;
    private LocalDateTime timestamp;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRepository() { return repository; }
    public void setRepository(String repository) { this.repository = repository; }

    public String getCommitId() { return commitId; }
    public void setCommitId(String commitId) { this.commitId = commitId; }

    public List<String> getSuggestions() { return suggestions; }
    public void setSuggestions(List<String> suggestions) { this.suggestions = suggestions; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
