package com.kuku.exercise.domain.service;

import com.kuku.exercise.domain.TestEntity2;
import com.kuku.exercise.domain.repository.Test2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class Test2DomainService {

    private final Test2Repository test2Repository;

    @Transactional
    public TestEntity2 getTestEntity2(Long key) {
        return test2Repository.findById(key)
                .orElseThrow(RuntimeException::new);
    }
}
