package com.kuku.exercise.ui;

import com.kuku.exercise.application.RequiresNewTestService;
import com.kuku.exercise.application.TestEventService;
import com.kuku.exercise.application.TestService;
import com.kuku.exercise.domain.service.ParentDomainService;
import com.kuku.exercise.domain.service.Test2DomainService;
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
    private final Test2DomainService test2DomainService;
    private final RequiresNewTestService requiresNewTestService;
    private final ParentDomainService parentDomainService;

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

    @GetMapping("/bulk/save")
    public void bulkSaveTest() {
        parentDomainService.saveEntity();
    }

    @GetMapping("/delete")
    public void delete() {
        test2DomainService.deleteAfterSave();
    }
}
