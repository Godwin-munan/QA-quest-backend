package com.munan.qatestservice.service;

import com.munan.qatestservice.entities.QuestionResponse;
import com.munan.qatestservice.entities.QuestionWrapper;
import com.munan.qatestservice.entities.Test;
import com.munan.qatestservice.entities.TestQuestions;
import com.munan.qatestservice.feign.TestInterface;
import com.munan.qatestservice.repository.TestQuestionsRepository;
import com.munan.qatestservice.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository repository;
    private final TestQuestionsRepository qaRepo;
    private final TestInterface testInterface;

    @Transactional
    public Mono<ResponseEntity<?>> createTest(String category, Integer numQ, String title) {
        return testInterface.getQuestionsIdForTest(category, numQ)
                .flatMap(res -> {
                    Test newTest = Test.builder()
                            .title(title)
                            .build();

                    return repository.save(newTest)
                            .flatMap(test -> {
                                insertTestQuestions(test.getId(), res).subscribe();
                                return Mono.just(test);
                            })
                            .map(test -> new ResponseEntity<>(test, HttpStatus.CREATED));
//                            .onErrorResume(error -> Mono.just(new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)));
                });
    }




    @Transactional
    public Mono<ResponseEntity<List<QuestionWrapper>>> getTestQuestions(Long testId) {

        return qaRepo.findByTestId(testId)
                .collectList()
                .flatMap(qIds -> testInterface.getQuestionsForTest(Mono.just(qIds)))
                .map(qw -> new ResponseEntity<>(qw, HttpStatus.OK));

    }


    public Mono<ResponseEntity<Integer>> submitTest(Long testId, Mono<List<QuestionResponse>> monoResponses) {

        return repository.findById(testId)
                    .flatMap(test -> testInterface.getTestScore(monoResponses))
                    .map(score -> new ResponseEntity<>(score, HttpStatus.OK));
    }


    /* Private method to save questions id in test_questions table*/
    private Mono<Void> insertTestQuestions(Long testId, List<Long> questionIds) {
        return Flux.fromIterable(questionIds)
                .concatMap(qId -> {
                    TestQuestions tQ = TestQuestions.builder()
                            .testId(testId)
                            .questionId(qId)
                            .build();

                    return qaRepo.save(tQ).then();
                })
                .collectList()
                .then();
    }
}


/**
 SAMPLE CODE FOR FEATURE USE
     return qaRepo.save(tQ).thenReturn("Hello world");
     return db.sql("INSERT INTO test_questions (test_id, question_id) VALUES (:testId, :questionId)")
     .bind("testId", testId)
     .bind("questionId", qId)
     .map(row -> TestQuestions.builder().id(row.get("id", Long.class))
     .testId(testId)
     .questionId(qId)
     .build())
     .one();

     return db.sql("INSERT INTO test_questions (test_id, question_id) VALUES (?, ?)")
     .bind(0, testId)
     .bind(1, tQ.getQuestionId())
     .then();

    return r2dbcEntityTemplate.insert(tQ).then();
 */
