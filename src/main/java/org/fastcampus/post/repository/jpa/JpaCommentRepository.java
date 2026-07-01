package org.fastcampus.post.repository.jpa;

import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {
  @Modifying
  @Query(value = " UPDATE CommentEntity ce" +
      "  SET ce.content = :#{#commentEntity.getContent()}" +
      ", ce.uptDt = now()" +
      " WHERE ce.id = :#{#commentEntity.getId()} ")
  void updateCommentEntity(CommentEntity comment);

  @Modifying
  @Query(value = " UPDATE CommentEntity ce " +
      " SET ce.likeCount = ce.likeCount + :likeCount} " +
      ", ce.uptDt = now() " +
      " WHERE ce.id = :commentId} ")
  void updateLikeCount(Long commentId, Integer likeCount);
}
