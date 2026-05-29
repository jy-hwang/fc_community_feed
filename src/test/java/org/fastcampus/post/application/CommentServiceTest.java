package org.fastcampus.post.application;

import org.fastcampus.post.domain.comment.Comment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentServiceTest extends PostApplicationTestTemplate {

  @Test
  void givenCreateCommentRequestDto_whenCreateComment_thenReturnComment() {
    // when
    Comment comment = commentService.createComment(commentRequestDto);

    // then
    String content = comment.getContent();
    assertEquals(commentContentText, content);
  }
}
