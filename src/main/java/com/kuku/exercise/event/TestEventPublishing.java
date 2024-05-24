package com.kuku.exercise.event;

import com.kuku.exercise.domain.TestEntity;
import com.kuku.exercise.domain.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestEventPublishing {

    private final TestRepository testRepository;

    @EventListener
    public void testSaveEvent(TestEntity test) {
        testRepository.save(test);
    }
}
