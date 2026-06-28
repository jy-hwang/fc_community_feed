package org.fastcampus.post.repository.jpa;


import org.fastcampus.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {
  @Modifying
  @Query(value = " UPDATE PostEntity pe" +
      "  SET pe.content = :#{#postEntity.getContent()}" +
      ", pe.state = :#{#postEntity.getState()}" +
      ", pe.uptDt = now()" +
      " WHERE pe.id = :#{#postEntity.getId()} ")
  void updatePostEntity(PostEntity postEntity);

  @Modifying
  @Query(value = " UPDATE PostEntity pe " +
      " SET pe.likeCount = :#{#postEntity.getLikeCount()} " +
      ", pe.uptDt = now() " +
      " WHERE pe.id = :#{#postEntity.getId()} ")
  void updateLikeCount(PostEntity postEntity);
}
