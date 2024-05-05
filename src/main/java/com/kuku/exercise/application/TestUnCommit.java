package com.kuku.exercise.application;

import com.kuku.exercise.domain.TestEntity;
import com.kuku.exercise.domain.TestEntity2;
import com.kuku.exercise.domain.repository.TestRepository;
import com.kuku.exercise.domain.service.Test2DomainService;
import com.kuku.exercise.domain.service.TestDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestUnCommit {

    private final TestRepository testRepository;

    private final TestDomainService testDomainService;
    private final Test2DomainService test2DomainService;

    @Transactional
    public void uncommittedTest() {

        TestEntity testEntity = TestEntity.builder()
                .name("test")
                .value("hi")
                .build();

        TestEntity saved = testRepository.save(testEntity);



    }
}
