package com.kuku.exercise.domain.data;

import lombok.Builder;

@Builder
public record TestData(
        boolean isBoolean,

        Boolean wrapperBoolean
) {
}
