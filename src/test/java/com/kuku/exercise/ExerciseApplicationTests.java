package com.kuku.exercise;

import com.kuku.exercise.domain.data.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class ExerciseApplicationTests {

	@Test
	void 레코드_primitive_boolean_먹는지확인() {

		TestData testData = TestData.builder().build();

		System.out.println(testData);
	}

}
