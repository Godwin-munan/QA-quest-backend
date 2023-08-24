package com.munan.qatestservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.List;

@Table("test")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column("title")
    private String title;

    @Column("question_id")
    private List<Long> questionIds;

}
