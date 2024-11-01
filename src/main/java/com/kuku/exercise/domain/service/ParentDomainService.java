package com.kuku.exercise.domain.service;

import com.kuku.exercise.domain.ChildEntity;
import com.kuku.exercise.domain.ParentEntity;
import com.kuku.exercise.domain.repository.ChildEntityRepository;
import com.kuku.exercise.domain.repository.ParentEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 10_000 * 10
 *
 * save : endTime : 12939ms.
 * saveAll : endTime : 13103ms.
 *
 * 100_000 * 10
 *
 * save : endTime : 133713ms.
 * saveAll : endTime : 126892ms.
 * bulk_insert : endTime : 86785ms.
 *
 */
@Service
@RequiredArgsConstructor
public class ParentDomainService {

    private final ParentEntityRepository parentEntityRepository;
    private final ChildEntityRepository childEntityRepository;

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveEntity() {

        long start = System.currentTimeMillis();

        for (int i = 0; i < 100_000; i++) {

            ParentEntity parent = ParentEntity.builder()
                    .name(String.valueOf(i))
                    .build();

            ParentEntity savedParent = parentEntityRepository.save(parent);

            List<ChildEntity> toChildEntities = new ArrayList<>();

            for (int t = 0; t < 10; t++) {

                ChildEntity child = ChildEntity.builder()
                        .parentId(savedParent.getId())
                        .build();

                toChildEntities.add(child);

                // 1. 단건 save
//                childEntityRepository.save(child);
            }

            // 2. saveAll
//            childEntityRepository.saveAll(toChildEntities);

            // 3. bulk insert jdbc template 사용
            jdbcBulkInsert(toChildEntities, savedParent.getId());

            System.out.println("endTime : "  + (System.currentTimeMillis() - start) + "ms.");
        }
    }

    private void jdbcBulkInsert(List<ChildEntity> childEntities,
                                Long parentId) {

        String sql = "INSERT INTO child_entity (parent_id) values (?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ChildEntity childEntity = childEntities.get(i);
                ps.setString(1, String.valueOf(parentId));
            }

            @Override
            public int getBatchSize() {
                return childEntities.size();
            }
        });
    }
}
