package com.munan.qatestservice.repository;

import com.munan.qatestservice.entities.TestQuestions;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQuestionsRepository extends R2dbcRepository<TestQuestions, Long> {

}





/*
SAMPLE CODE FOR FUTURE USE

    @Query("INSERT INTO test_questions (test_id, question_id) VALUES (:testId, :questionId);")
    Mono<Integer> insertTestQuestion(Long testId, Long questionId);
*/
