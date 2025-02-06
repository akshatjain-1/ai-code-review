package com.example.codereview.repository;

import com.example.codereview.model.CodeReviewLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeReviewLogRepository extends ElasticsearchRepository<CodeReviewLog, String> {
}
