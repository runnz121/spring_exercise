package com.kuku.exercise.application;

import com.kuku.exercise.domain.TestEntity;
import com.kuku.exercise.domain.repository.TestRepository;
import com.kuku.exercise.domain.service.TestDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 트랜잭션 중간에 에러 발생해도 저장이 되어야 할때 확인해보는 테스트
 *
 */
@Service
@RequiredArgsConstructor
public class RequiresNewTestService {

    private final TestDomainService testDomainService;

    // 결과 : 에러가 발생해도 저장된다.
    @Transactional
    public void callMethod() {
        // 엔티티를 저장안하고 만들어서 넘기는 경우 됨.
        TestEntity entity = TestEntity.builder()
                .name("new transaction")
                .build();
        // 새로운 트랜젝션임으로 저장이 되어야 한다.
        testDomainService.newTransactionSave(entity);

        throw new RuntimeException();
    }

    // 결과 : 저장됨
    @Transactional
    public void preSavedEntity() {

        // 1. 엔티티 생성
        TestEntity entity = TestEntity.builder()
                .name("pre saved transaction")
                .build();
        // 2. 영속화 상태
        testDomainService.save(entity);
        // 3. 엔티티 변경
        entity.changeName("changed Name");
        // 4. 엔티티 저장
        testDomainService.newTransactionSave(entity);

        throw new RuntimeException();
    }

    @Transactional
    public void searchEntity() {

        // 1. 엔티티 생성
        TestEntity entity = TestEntity.builder()
                .name("pre saved transaction")
                .build();
        // 2. 영속화 상태
        TestEntity savedEntity = testDomainService.save(entity);
        // 3. 엔티티 변경
        entity.changeName("changed searchedEntity Name");
        // 4. 엔티티 저장
        testDomainService.newTransactionSave(entity);

        throw new RuntimeException();
    }
}
