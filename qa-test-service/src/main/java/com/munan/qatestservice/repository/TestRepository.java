package com.munan.qatestservice.repository;

import com.munan.qatestservice.entities.Test;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends R2dbcRepository<Test, Long> {

}
