package com.munan.qatestservice.service;

import com.munan.qatestservice.entities.QuestionWrapper;
import com.munan.qatestservice.feign.TestInterface;
import com.munan.qatestservice.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository repository;
    private final TestInterface testInterface;

    public Mono<ResponseEntity<String>> createTest(String category, Integer numQ, String title) {
        return null;
    }

    public Mono<ResponseEntity<List<QuestionWrapper>>> getTestQuestions(Long id) {
        return null;
    }


    public Mono<ResponseEntity<Integer>> submitTest(Long id) {
        return null;
    }
}
