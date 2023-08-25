package com.munan.qaquestionservice.controller;


import com.munan.qaquestionservice.entities.Question;
import com.munan.qaquestionservice.entities.QuestionResponse;
import com.munan.qaquestionservice.entities.QuestionWrapper;
import com.munan.qaquestionservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService service;

    @PostMapping
    public Mono<ResponseEntity<Question>>  createNewQuestion(@RequestBody Question question){
        return service.createQuestion(question);
    }

    @GetMapping
    public Mono<ResponseEntity<List<Question>>> getAllQuestionsInDb(){
        return service.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public Mono<ResponseEntity<List<Question>>> getQuestionByCategory(@PathVariable String category){
        return service.getByCategory(category);
    }

    @GetMapping("/generate")
    public Mono<List<Long>> getQuestionsIdForTest(@RequestParam("category") String category, @RequestParam("numQ") Integer numQ){
        System.out.println(" ---------------------------------------------  SHOW QUESTION CONTROLLER  ---------------------------------------------");
        return service.getQuestionsIds(category,numQ);
    }

    @PostMapping("/getQuestions")
    public Mono<List<QuestionWrapper>> getQuestionsForTest(@RequestBody Mono<List<Long>> questionsIdMono){
        return service.getQuestions(questionsIdMono);
    }

    @PostMapping("/getScore")
    public Mono<Integer> getTestScore(@RequestBody Mono<List<QuestionResponse>> resListMono){
        return service.getScore(resListMono);
    }

}
