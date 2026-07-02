package org.fastcampus.acceptance.utils;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Profile("test")
@Component
@Slf4j
public class DatabaseCleanup implements InitializingBean {
  @PersistenceContext
  private EntityManager entityManager;
  private List<String> tableNames;
  private List<String> notGeneratedIdTableNames;

  @Override
  public void afterPropertiesSet() throws Exception {
    tableNames = entityManager.getMetamodel().getEntities()
        .stream()
        .filter(entity -> entity.getJavaType().getAnnotation(Entity.class) != null)
        .map(entity -> entity.getJavaType().getAnnotation(Table.class).name())
        .toList();

    notGeneratedIdTableNames = List.of("community_user_relation", "community_like");
  }

  @Transactional
  public void execute() {
    // flush : 현재 영속성 컨텍스트(메모리)에 쌓여 있는 엔티티의 모든 변경 사항(INSERT, UPDATE, DELETE)을 데이터베이스에 즉시 쿼리로 전송
    entityManager.flush();
    // 데이터베이스의 참조 무결성 제약 조건(외래키/FK 제약 조건)을 일시적으로 비활성화
    entityManager.createNativeQuery(" SET REFERENTIAL_INTEGRITY FALSE ").executeUpdate();
    for (String tableName : tableNames) {
      if (!notGeneratedIdTableNames.contains(tableName)) {
        entityManager.createNativeQuery(" ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1 ").executeUpdate();
      }
    }
    entityManager.createNativeQuery(" SET REFERENTIAL_INTEGRITY TRUE ").executeUpdate();
  }
}
