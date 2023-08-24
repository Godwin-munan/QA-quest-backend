package com.munan.qatestservice.feign;

import com.munan.qatestservice.entities.QuestionResponse;
import com.munan.qatestservice.entities.QuestionWrapper;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface TestInterface {

    @GetMapping("generate")
    public Mono<ResponseEntity<List<Long>>> getQuestionsIdForTest(@RequestParam("category") String category, @RequestParam("numQ") Long numQ);

    @PostMapping("getQuestions")
    public Mono<ResponseEntity<List<QuestionWrapper>>> getQuestionsForTest(@RequestBody Mono<List<Long>> questionsIdMono);

    @PostMapping("getScore")
    public Mono<ResponseEntity<Integer>> getTestScore(@RequestBody Mono<List<QuestionResponse>> resListMono);
}
