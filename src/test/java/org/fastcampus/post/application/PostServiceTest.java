package org.fastcampus.post.application;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdatePostRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostServiceTest {
  private final UserService userService = FakeObjectFactory.getUserService();
  private final PostService postService = FakeObjectFactory.getPostService();

  private final User user = userService.createUser(new CreateUserRequestDto("user1", null));
  private final User otherUser = userService.createUser(new CreateUserRequestDto("user1", null));

  private CreatePostRequestDto dto = new CreatePostRequestDto(user.getId(), "this is test Content", PostPublicationState.PUBLIC);


  @Test
  void givenPostRequestDto_whenCreate_thenReturnPost() {
    // when
    Post savedPost = postService.createPost(dto);

    // then
    Post post = postService.getPost(savedPost.getId());
    assertEquals(savedPost, post);
  }

  @Test
  void givenCreatePost_whenUpdate_thenReturnUpdatedPost() {
    // given
    Post savedPost = postService.createPost(dto);
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
    Post savedPost = postService.createPost(dto);

    // when
    LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.likePost(likeRequestDto);

    // then
    assertEquals(1, savedPost.getLikeCounter());
  }

  @Test
  void givenCreatePost_whenLikedTwice_thenReturnPostWithLike() {
    // given
    Post savedPost = postService.createPost(dto);

    // when
    LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.likePost(likeRequestDto);
    postService.likePost(likeRequestDto);

    // then
    assertEquals(1, savedPost.getLikeCounter());
  }

  @Test
  void givenCreatePostLiked_whenUnliked_thenReturnPostWithoutLike() {
    // given
    Post savedPost = postService.createPost(dto);
    LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.likePost(likeRequestDto);

    // when
    postService.unlikePost(likeRequestDto);

    // then
    assertEquals(0, savedPost.getLikeCounter());
  }

  @Test
  void givenCreatePost_whenUnliked_thenReturnPostWithoutLike() {
    // given
    Post savedPost = postService.createPost(dto);

    // when
    LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.unlikePost(likeRequestDto);

    // then
    assertEquals(0, savedPost.getLikeCounter());
  }
}
