package com.munan.qatestservice.entities;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TestMapper {

    private Long testId;
    private List<Long> questionIds;
}
