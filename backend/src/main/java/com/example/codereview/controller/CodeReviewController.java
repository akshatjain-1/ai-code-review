package com.example.codereview.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/")
public class CodeReviewController {

    @PostMapping("/review")
    public ResponseEntity<Map<String, Object>> reviewCode(@RequestBody Map<String, String> request) {
        String code = request.get("code");  // Extract code from request

        // Simple AI response (placeholder)
        String suggestion = code.contains("eval") 
            ? "Avoid using eval() due to security risks."
            : "No issues detected.";

        Map<String, Object> response = Collections.singletonMap("suggestions", Collections.singletonList(suggestion));
        return ResponseEntity.ok(response);
    }
}
