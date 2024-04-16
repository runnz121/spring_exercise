package com.kuku.exercise.application;

import com.kuku.exercise.domain.TestEntity;
import com.kuku.exercise.domain.repository.TestRepository;
import com.kuku.exercise.domain.service.TestDomainService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestServiceTest {

    @Autowired
    TestDomainService testDomainService;

    @Autowired
    TestRepository testRepository;

    Long givenId;

    int totalQuantity = 1000;

    @BeforeEach
    void setUp() {
        TestEntity entity = TestEntity.builder()
                .totalQuantity(totalQuantity)
                .value("test1")
                .build();

        givenId = testRepository.saveAndFlush(entity).getId();
    }

    @AfterEach
    void tearDown() {
        testRepository.deleteAll();
    }

    @Test
    void 동시성_테스트1() throws InterruptedException {

        int threadCount = 10;
        int decreaseCount = 5;

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                try {
                    testDomainService.decreaseEntityWithLock(givenId, decreaseCount);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        TestEntity test = testRepository.getById(givenId);

        assertThat(test.getTotalQuantity()).isEqualTo(totalQuantity - (threadCount * decreaseCount));

        System.out.println(test);
    }

    @Test
    void 동시성_테스트2() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(10);

        List<TesteWorkerWithPessimisticLock> workers = Stream
                .generate(() -> new TesteWorkerWithPessimisticLock(givenId, countDownLatch))
                .limit(10)
                .toList();

        workers.forEach(worker -> new Thread(worker).start());
        countDownLatch.await();

        TestEntity findEntity = testRepository.findByTestEntityWithValue("test1").orElseThrow();

        System.out.println(findEntity);
    }

    private class TesteWorkerWithPessimisticLock implements Runnable {
        private CountDownLatch countDownLatch;
        private Long id;

        public TesteWorkerWithPessimisticLock(Long id, CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
            this.id = id;
        }

        @Override
        public void run() {
            testDomainService.decreaseEntityWithLock(id, 5);
            countDownLatch.countDown();
        }
    }
}