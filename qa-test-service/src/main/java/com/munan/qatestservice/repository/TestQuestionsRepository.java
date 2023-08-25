package com.munan.qatestservice.repository;

import com.munan.qatestservice.entities.TestQuestions;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQuestionsRepository extends R2dbcRepository<TestQuestions, Long> {
}
