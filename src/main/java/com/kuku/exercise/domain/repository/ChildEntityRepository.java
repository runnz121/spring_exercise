package com.kuku.exercise.domain.repository;

import com.kuku.exercise.domain.ChildEntity;
import org.springframework.data.repository.CrudRepository;

public interface ChildEntityRepository extends CrudRepository<ChildEntity, Long> {
}
