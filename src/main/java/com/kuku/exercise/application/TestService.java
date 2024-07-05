package com.kuku.exercise.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuku.exercise.domain.TestEntity;
import com.kuku.exercise.domain.repository.TestRepository;
import com.kuku.exercise.domain.service.TestDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class TestService {

    private final ObjectMapper objectMapper;
    private final TestRepository testRepository;
    private final TestDomainService testDomainService;

    @Transactional
    public void saveEntity() {
        TestEntity entity = TestEntity.builder()
                .value("test")
                .totalQuantity(1000)
                .build();

        testRepository.save(entity);
    }

    @Transactional
    public void multiThreadTest(Long id) throws InterruptedException {
        multiThreadTest(10, id);
    }

    private void multiThreadTest(int count, Long id) throws InterruptedException {

        int numThreads = 5;

        CountDownLatch doneSignal = new CountDownLatch(numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        for (int i = 0; i < count; i++) {
            executorService.execute(() -> {
                try{
                    testDomainService.decreaseEntityWithLock(id, 5);
                    successCount.getAndIncrement();
                }catch (Exception e){
                    e.printStackTrace();
                    failCount.getAndIncrement();
                } finally {
                    doneSignal.countDown();
                }
            });
        }
        doneSignal.await();
        executorService.shutdown();
    }

    @Transactional
    public void saveEntityTest() {
        testDomainService.saveNullEntity();
    }

    @Transactional
    public TestEntity getTestEntity(Long id) {
        TestEntity testEntity = testRepository.getById(id);

        TestEntity.Optional optional = null;
        try {
            optional = objectMapper.readValue(testEntity.getOptional(), TestEntity.Optional.class);
        } catch (Exception ex) {

        }
        System.out.println(optional);

        if (optional.isReturnable()) {
            System.out.println("true");
        } else {
            System.out.println("False");
        }
        return testEntity;
    }
}
