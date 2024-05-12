package com.kuku.exercise.ui;

import com.kuku.exercise.application.TestEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController1 {

    private final TestEventService testEventService;
    @GetMapping("/event/{id}")
    public void testController11(@PathVariable("id") String id) {
        testEventService.eventPublish(id);
    }
}
