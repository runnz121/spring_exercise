package com.kuku.exercise.infrastructure.controller;

import com.kuku.exercise.application.TestService;
import com.kuku.exercise.domain.TestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/save")
    public void saveEntity() {
        testService.saveEntity();
    }

    @GetMapping("/concurrent/{id}")
    public void concurrent(@PathVariable("id") Long id) throws InterruptedException {
        testService.multiThreadTest(id);
    }

    @GetMapping("/get/entity/{id}")
    public TestEntity getTestEntity(@PathVariable("id") Long id) {
        return testService.getTestEntity(id);
    }

}
