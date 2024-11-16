package com.kuku.exercise.domain.service;

import com.kuku.exercise.domain.ChildEntity;
import com.kuku.exercise.domain.ParentEntity;
import com.kuku.exercise.domain.TestEntity2;
import com.kuku.exercise.domain.repository.ChildEntityRepository;
import com.kuku.exercise.domain.repository.ParentEntityRepository;
import com.kuku.exercise.domain.repository.Test2Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class Test2DomainService {

    private final ParentEntityRepository parentEntityRepository;
    private final ChildEntityRepository childEntityRepository;
    private final Test2Repository test2Repository;

    @Transactional
    public TestEntity2 getTestEntity2(Long key) {
        return test2Repository.findById(key)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    public void saveTestEntity2(TestEntity2 testEntity2) {
        test2Repository.save(testEntity2);
    }

    @Transactional
    public void deleteAfterSave() {
        ParentEntity parent = ParentEntity.builder()
                .name("parent1")
                .build();

        ParentEntity savedParent = parentEntityRepository.save(parent);

        ChildEntity child1 = ChildEntity.builder()
                .parentId(savedParent.getId())
                .name("child1")
                .build();


        ChildEntity child2 = ChildEntity.builder()
                .parentId(savedParent.getId())
                .name("child2")
                .build();

        List<ChildEntity> childLists = List.of(child1, child2);

        childEntityRepository.saveAll(childLists);

        log.info("save finished");

        parentEntityRepository.delete(parent);
        childEntityRepository.deleteAll(childLists);
    }
}
