package com.kuku.exercise.exe;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestExe {

    private static final Integer flags = -1;

    @Test
    void 테스트() {
        int flag = -1;

        Boolean res = -1 == flags;

        System.out.println(res);

//        assertThat(flag).isEqualTo(-1);
//        assertThat(flag).isEqualTo(1);
//        System.out.println(flag);
    }
}
