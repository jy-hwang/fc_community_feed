package org.fastcampus.post.repository.jpa;


import org.fastcampus.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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
      " SET pe.likeCount = pe.likeCount + :likeCount " +
      ", pe.uptDt = now() " +
      " WHERE pe.id = :postId ")
  void updateLikeCount(Long postId, Integer likeCount);

  @Modifying
  @Query(value = " UPDATE PostEntity pe " +
      " SET pe.commentCount = pe.commentCount + 1 " +
      ", pe.uptDt = now() " +
      " WHERE pe.id = :id ")
  void increaseCommentCount(Long id);

  @Query("SELECT pe FROM PostEntity pe WHERE pe.author.id = :authorId")
  List<PostEntity> findAllPostIdsByAuthorId(Long authorId);
}
