package com.example.codereview.model;

import java.util.List;

public class AnalysisResponse {
    private List<String> suggestions;

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }
}
