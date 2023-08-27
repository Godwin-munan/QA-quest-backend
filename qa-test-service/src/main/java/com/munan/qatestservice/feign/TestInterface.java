package com.munan.qatestservice.feign;

import com.munan.qatestservice.entities.QuestionResponse;
import com.munan.qatestservice.entities.QuestionWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

import java.util.List;

@ReactiveFeignClient(name= "QUESTION-SERVICE", url = "${question.url}")
public interface TestInterface {

    @GetMapping("/api/v1/questions/generate")
    Mono<List<Long>> getQuestionsIdForTest(@RequestParam("category") String category, @RequestParam("numQ") Integer numQ);

    @PostMapping("/api/v1/questions/question-list")
    Mono<List<QuestionWrapper>> getQuestionsForTest(@RequestBody Mono<List<Long>> questionsIdMono);

    @PostMapping("/api/v1/questions/score")
    Mono<Integer> getTestScore(@RequestBody Mono<List<QuestionResponse>> resListMono);
}
