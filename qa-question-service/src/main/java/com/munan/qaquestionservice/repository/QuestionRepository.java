package com.munan.qaquestionservice.repository;

import com.munan.qaquestionservice.entities.Question;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface QuestionRepository extends R2dbcRepository<Question, Long> {

    Flux<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM question q WHERE q.category=:category ORDER BY RAND() LIMIT :numQ")
    Flux<Long> findRandomQuestionsByCategory(String category, Long numQ);
}
