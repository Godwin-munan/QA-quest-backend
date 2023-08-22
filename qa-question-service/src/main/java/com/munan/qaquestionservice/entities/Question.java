package com.munan.qaquestionservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Table("question")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column("question_title")
    private String questionTitle;

    @Column("option1")
    private String option1;

    @Column("option2")
    private String option2;

    @Column("option3")
    private String option3;

    @Column("option4")
    private String option4;

    @Column("correct_answer")
    private String correctAnswer;

    @Column("difficulty_level")
    private String difficultyLevel;

    @Column("category")
    private String category;


}
