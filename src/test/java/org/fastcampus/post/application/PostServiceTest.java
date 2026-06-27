package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdatePostRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostServiceTest extends PostApplicationTestTemplate {

  @Test
  void givenPostRequestDto_whenCreate_thenReturnPost() {
    // when
    Post savedPost = postService.createPost(postRequestDto);

    // then
    Post post = postService.getPost(savedPost.getId());
    assertEquals(savedPost, post);
  }

  @Test
  void givenCreatePost_whenUpdate_thenReturnUpdatedPost() {
    // given
    Post savedPost = postService.createPost(postRequestDto);
    UpdatePostRequestDto updateDto
        = new UpdatePostRequestDto(savedPost.getId(), savedPost.getAuthor().getId(), "this is updated content", PostPublicationState.PUBLIC);

    // when
    Post updatedPost = postService.updatePost(savedPost.getId(), updateDto);

    // then
    assertEquals(savedPost.getId(), updatedPost.getId());
    assertEquals(savedPost.getAuthor(), updatedPost.getAuthor());
    assertEquals(savedPost.getContent(), updatedPost.getContent());
  }

  @Test
  void givenCreatePost_whenLiked_thenReturnPostWithLike() {
    // given
    Post savedPost = postService.createPost(postRequestDto);

    // when
    LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.likePost(likeRequestDto);

    // then
    assertEquals(1, savedPost.getLikeCount());
  }

  @Test
  void givenCreatePost_whenLikedTwice_thenReturnPostWithLike() {
    // given
    Post savedPost = postService.createPost(postRequestDto);

    // when
    LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.likePost(likeRequestDto);
    postService.likePost(likeRequestDto);

    // then
    assertEquals(1, savedPost.getLikeCount());
  }

  @Test
  void givenCreatePostLiked_whenUnliked_thenReturnPostWithoutLike() {
    // given
    Post savedPost = postService.createPost(postRequestDto);
    LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.likePost(likeRequestDto);

    // when
    postService.unlikePost(likeRequestDto);

    // then
    assertEquals(0, savedPost.getLikeCount());
  }

  @Test
  void givenCreatePost_whenUnliked_thenReturnPostWithoutLike() {
    // given
    Post savedPost = postService.createPost(postRequestDto);

    // when
    LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.unlikePost(likeRequestDto);

    // then
    assertEquals(0, savedPost.getLikeCount());
  }
}
