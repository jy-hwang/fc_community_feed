package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.CreateCommentRequestDto;
import org.fastcampus.post.application.dto.UpdateCommentRequestDto;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;

public class CommentService {
  private final CommentRepository commentRepository;
  private final UserService userService;
  private final PostService postService;

  public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
    this.commentRepository = commentRepository;
    this.userService = userService;
    this.postService = postService;
  }

  public Comment getComment(Long commentId) {
    return commentRepository.findById(commentId).orElseThrow(IllegalAccessError::new);
  }

  public Comment createComment(CreateCommentRequestDto dto) {
    Post post = postService.getPost(dto.postId());
    User user = userService.getUser(dto.userId());

    Comment comment = Comment.createComment(post, user, dto.content());
    return commentRepository.save(comment);
  }

  public Comment updateComment(UpdateCommentRequestDto dto) {
    Comment comment = getComment(dto.commentId());
    User user = userService.getUser(dto.userId());

    comment.updateComment(user, dto.content());
    return commentRepository.save(comment);
  }
}

