package com.munan.qatestservice.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

import java.util.List;

@ReactiveFeignClient(name= "QUESTION-SERVICE", url = "${question.url}")
public interface TestInterface {

    @GetMapping("/question/generate")
    public Mono<List<Long>> getQuestionsIdForTest(@RequestParam("category") String category, @RequestParam("numQ") Integer numQ);

//    @PostMapping("getQuestions")
//    public Mono<ResponseEntity<List<QuestionWrapper>>> getQuestionsForTest(@RequestBody Mono<List<Long>> questionsIdMono);
//
//    @PostMapping("getScore")
//    public Mono<ResponseEntity<Integer>> getTestScore(@RequestBody Mono<List<QuestionResponse>> resListMono);
}
