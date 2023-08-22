package com.munan.qaquestionservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionWrapper {

    private Long id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

}
