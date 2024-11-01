package com.kuku.exercise.domain.repository;

import com.kuku.exercise.domain.ParentEntity;
import org.springframework.data.repository.CrudRepository;

public interface ParentEntityRepository extends CrudRepository<ParentEntity, Long> {
}
