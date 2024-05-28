package com.kuku.exercise.ui;

import com.kuku.exercise.application.RequiresNewTestService;
import com.kuku.exercise.application.TestEventService;
import com.kuku.exercise.application.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController1 {

    private final TestService testService;
    private final TestEventService testEventService;
    private final RequiresNewTestService requiresNewTestService;

    @GetMapping("/event/{id}")
    public void testController11(@PathVariable("id") String id) {
        testEventService.eventPublish(id);
    }

    @GetMapping("/new-transaction")
    public void newTransactionController() {
        requiresNewTestService.callMethod();
    }

    @GetMapping("/pre-transaction")
    public void preSavedTransactionController() {
        requiresNewTestService.preSavedEntity();
    }

    @GetMapping("/null")
    public void saveEntityTest() {
        testService.saveEntityTest();
    }
}
