package com.munan.qatestservice.controller;

import com.munan.qatestservice.dto.TestDto;
import com.munan.qatestservice.entities.QuestionWrapper;
import com.munan.qatestservice.entities.Test;
import com.munan.qatestservice.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService service;

    @PostMapping
    public Mono<ResponseEntity<?>> createNewTest(@RequestBody TestDto testDto){
        return service.createTest(testDto.getCategory(), testDto.getNumQ(), testDto.getTitle());
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<List<QuestionWrapper>>> getNewTestQuestions(@PathVariable Long id){
        return service.getTestQuestions(id);
    }

    @PostMapping("{id}")
    public Mono<ResponseEntity<Integer>> submitTestScore(@PathVariable Long id){
        return service.submitTest(id);
    }

}
