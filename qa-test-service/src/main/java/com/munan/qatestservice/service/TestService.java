package com.munan.qatestservice.service;

import com.munan.qatestservice.entities.QuestionWrapper;
import com.munan.qatestservice.entities.Test;
import com.munan.qatestservice.entities.TestMapper;
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
    public Mono<ResponseEntity<String>> createTest(String category, Integer numQ, String title) {

        return testInterface.getQuestionsIdForTest(category, numQ).flatMap(res -> {
            Test newTest = Test.builder()
                    .title(title)
                    .build();

            System.out.println("QUETIONS LIST: "+res);

            return repository.save(newTest).map(test -> Flux.fromIterable(res).flatMap(qId -> {
                TestQuestions tQ = TestQuestions.builder().testId(test.getId()).questionId(qId).build();
                return qaRepo.save(tQ);
            }).collectList().thenReturn(test))
                    .map(test ->  new ResponseEntity<>("Successfull", HttpStatus.OK));
        });

    }

    public Mono<ResponseEntity<List<QuestionWrapper>>> getTestQuestions(Long id) {
        return null;
    }


    public Mono<ResponseEntity<Integer>> submitTest(Long id) {
        return null;
    }
}
