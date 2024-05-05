package com.kuku.exercise.domain.service;

import com.kuku.exercise.domain.TestEntity;
import com.kuku.exercise.domain.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestDomainService {

    private final TestRepository testRepository;

    @Transactional
    public void decreaseEntityWithLock(Long id, int decreaseCount) {
        TestEntity entity = getTestEntityWithLock(id);
        entity.decreaseQuantity(decreaseCount);
        testRepository.saveAndFlush(entity);
    }

    @Transactional
    public void decreaseEntityWithOutLock(Long id) {
        TestEntity entity = getTestEntity(id);
        entity.decreaseQuantity(5);
    }

    @Transactional
    public TestEntity getTestEntityWithLock(Long id) {
        return testRepository.findByTestEntityWithPessimisticWrite(id)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    public TestEntity getTestEntity(Long value) {
        return testRepository.findByTestEntity(value)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    public TestEntity getTestEntityWithValue(String value) {
        return testRepository.findByTestEntityWithValue(value)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    public TestEntity getTestEntityByKey(Long key) {
        return testRepository.findByTestEntity(key)
                .orElseThrow(RuntimeException::new);
    }
}
