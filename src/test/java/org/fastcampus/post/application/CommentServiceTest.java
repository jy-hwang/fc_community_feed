package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.UpdateCommentRequestDto;
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

  @Test
  void givenCreateComment_whenUpdateCommment_thenReturnUpdatedComment(){
    // given
    Comment comment = commentService.createComment(commentRequestDto);

    // when
    UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(comment.getId(), user.getId(), "updated comment");
    Comment updatedComment = commentService.updateComment(updateCommentRequestDto);

    // then
    assertEquals(comment.getId(), updatedComment.getId());
    assertEquals(comment.getAuthor(), updatedComment.getAuthor());
    assertEquals(comment.getContent(), updatedComment.getContent());
  }
}
