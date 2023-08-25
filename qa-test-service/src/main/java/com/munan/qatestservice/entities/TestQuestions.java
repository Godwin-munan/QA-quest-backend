package com.munan.qatestservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Table("test_questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TestQuestions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column("test_id")
    private Long testId;

    @Column("question_id")
    private Long questionId;
}
