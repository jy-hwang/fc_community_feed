package org.fastcampus.post.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.common.ui.Response;
import org.fastcampus.post.application.CommentService;
import org.fastcampus.post.application.dto.CreateCommentRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdateCommentRequestDto;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.repository.CommentQueryRepositoryImpl;
import org.fastcampus.post.ui.dto.GetContentResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
  private final CommentService commentService;
  private final CommentQueryRepositoryImpl commentQueryRepository;

  @PostMapping
  public Response<Long> createComment(@RequestBody CreateCommentRequestDto dto) {
    Comment comment = commentService.createComment(dto);
    return Response.ok(comment.getId());
  }

  @PatchMapping("/{commentId}")
  public Response<Long> updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequestDto dto) {
    Comment comment = commentService.updateComment(commentId, dto);
    return Response.ok(comment.getId());
  }

  @PostMapping("/like")
  public Response<Long> likeComment(@RequestBody LikeRequestDto dto) {
    commentService.likeComment(dto);
    return Response.ok(null);
  }

  @PostMapping("/unlike")
  public Response<Void> unlikeComment(@RequestBody LikeRequestDto dto) {
    commentService.unlikeComment(dto);
    return Response.ok(null);
  }

  @GetMapping("/post/{postId}")
  public Response<List<GetContentResponseDto>> getCommentList(@PathVariable(name = "postId") Long postId, Long userId, Long lastCommentId) {
    List<GetContentResponseDto> commentList = commentQueryRepository.getCommentList(postId, userId, lastCommentId);
    return Response.ok(commentList);
  }
}
