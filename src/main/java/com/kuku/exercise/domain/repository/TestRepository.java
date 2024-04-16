package com.kuku.exercise.domain.repository;

import com.kuku.exercise.domain.TestEntity;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TestRepository extends JpaRepository<TestEntity, Long> {

    default TestEntity getById(Long id) {
        return findById(id).orElseThrow(RuntimeException::new);
    }


//    @QueryHints({
//            @QueryHint(name = "jakarta.persistence.lock.timeout", value = "100")
//    })
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select i from TestEntity i where i.id = :id")
    Optional<TestEntity> findByTestEntityWithPessimisticWrite(Long id);

    @Query("select i from TestEntity i where i.value = :id")
    Optional<TestEntity> findByTestEntity(@Param("id") Long id);

    @Query("select i from TestEntity i where i.value = :value")
    Optional<TestEntity> findByTestEntityWithValue(@Param("value") String value);
}
