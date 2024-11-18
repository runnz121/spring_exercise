package com.kuku.exercise.domain.service;

import com.kuku.exercise.domain.TestEntity;
import com.kuku.exercise.domain.TestEntity3;
import com.kuku.exercise.domain.repository.TestRepository;
import com.kuku.exercise.domain.repository.TestRepository3;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestDomainService {

    private final TestRepository testRepository;
    private final TestRepository3 testRepository3;

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

    @Transactional
    public TestEntity save(TestEntity testEntity) {
        return testRepository.save(testEntity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void newTransactionSave(TestEntity entity) {
        testRepository.save(entity);
    }

    @Transactional
    public void searchSavedEntity(TestEntity test) {

    }

    @Transactional
    public void saveNullEntity() {

        TestEntity test = null;

        TestEntity3 testEntity3 = TestEntity3.builder()
                .testEntity(test)
                .testEntityName(Optional.ofNullable(test).map(it -> it.getName()).orElse(null))
                .build();

        testRepository3.save(testEntity3);
    }

    public void sortTest() {
        testRepository.findAll();
    }
}
