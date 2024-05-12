package com.kuku.exercise.application;

import com.kuku.exercise.domain.TestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestEventService {

    private final ApplicationEventPublisher publisher;

    @Transactional
    public void eventPublish(String id) {
        TestEntity test = TestEntity.builder()
                .name("에러 임으로 롤백 안됨 !!!!!!!")
                .build();
        publisher.publishEvent(test);
        if (id.equals("hi")) {
            throw new RuntimeException();
        }
    }
}
