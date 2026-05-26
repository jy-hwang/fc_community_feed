package org.fastcampus.post.application;

import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.domain.comment.Comment;

public class CommentService {
  private final CommentRepository commentRepository;

  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public Comment getComment(Long commentId) {
    return commentRepository.findById(commentId).orElseThrow(IllegalAccessError::new);
  }
}

