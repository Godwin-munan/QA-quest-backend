package com.munan.qatestservice.repository;

import com.munan.qatestservice.entities.TestQuestions;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TestQuestionsRepository extends R2dbcRepository<TestQuestions, Long> {

    @Query("SELECT question_id FROM test_questions q WHERE q.test_id = :testId;")
    Flux<Long> findByTestId(Long testId);
}





/*
SAMPLE CODE FOR FUTURE USE

    @Query("INSERT INTO test_questions (test_id, question_id) VALUES (:testId, :questionId);")
    Mono<Integer> insertTestQuestion(Long testId, Long questionId);
*/
