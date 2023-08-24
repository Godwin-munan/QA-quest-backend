package com.munan.qaquestionservice.service;

import com.munan.qaquestionservice.entities.Question;
import com.munan.qaquestionservice.entities.QuestionResponse;
import com.munan.qaquestionservice.entities.QuestionWrapper;
import com.munan.qaquestionservice.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository repository;

    public Mono<ResponseEntity<Question>> createQuestion(Question question){
        return repository.save(question)
                .flatMap(q -> Mono.just(new ResponseEntity<>(q, HttpStatus.CREATED)))
                .onErrorResume(error -> Mono.just(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)));
    }


    public Mono<ResponseEntity<List<Question>>> getAllQuestions() {
        return repository.findAll()
                .collectList()
                .flatMap(question -> Mono.just( new ResponseEntity<>(question, HttpStatus.OK)))
                .onErrorResume(error -> Mono.just(new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR)));
    }


    public Mono<ResponseEntity<List<Question>>> getByCategory(String category) {
        return repository.findByCategory(category)
                .collectList()
                .flatMap(question -> Mono.just( new ResponseEntity<>(question, HttpStatus.OK)))
                .onErrorResume(error -> Mono.just(new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR)));
    }


    public Mono<ResponseEntity<List<Long>>> getQuestionsIds(String category, Long numQ) {
        return repository.findRandomQuestionsByCategory(category, numQ)
                .collectList()
                .flatMap(question -> Mono.just( new ResponseEntity<>(question, HttpStatus.OK)))
                .onErrorResume(error -> Mono.just(new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR)));
    }


    public Mono<ResponseEntity<List<QuestionWrapper>>> getQuestions(Mono<List<Long>> questionsIdMono) {
            return questionsIdMono.flatMap(ids -> repository.findAllById(ids)
                            .map(q -> QuestionWrapper.builder()
                                .id(q.getId())
                                .questionTitle(q.getQuestionTitle())
                                .option1(q.getOption1())
                                .option2(q.getOption2())
                                .option3(q.getOption3())
                                .option4(q.getOption4())
                                .build())
                            .collectList())
                    .map(questions -> new ResponseEntity<>(questions, HttpStatus.OK))
                    .onErrorResume(error -> Mono.just(new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR)));

    }


    public Mono<ResponseEntity<Integer>> getScore(Mono<List<QuestionResponse>> resListMono) {
        return resListMono.flatMap(res -> {
            Flux<QuestionResponse>  flux = Flux.fromIterable(res);

            return flux.flatMap(r -> repository.findById(r.getId())
                    .map(q -> r.getResponse().equals(q.getCorrectAnswer()) ? 1 : 0))
                    .reduce(0, Integer::sum)
                    .map(correct -> new ResponseEntity<>(correct, HttpStatus.OK));
        });
    }
}
