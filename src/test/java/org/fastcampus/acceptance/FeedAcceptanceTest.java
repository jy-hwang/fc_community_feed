package org.fastcampus.acceptance;

import org.fastcampus.acceptance.utils.AcceptanceTestTemplate;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.post.ui.dto.GetPostContentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.fastcampus.acceptance.steps.FeedAcceptanceSteps.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedAcceptanceTest extends AcceptanceTestTemplate {
  private String token;

  /**
   * User 1 -- follow --> User 2
   * User 1 -- follow --> User 3
   */
  @BeforeEach
  void setUp() {
    super.init();
    this.token = login("user1@test.xom");
  }

  /**
   * User 2 create Post 1
   * User 1 Get Post 1 From Feed
   */
  @Test
  void givenUserHasFollowerAndCreatePost_whenFollowerUserCreatePost_thenFollowerCanGetPostFromFeed() {
    // given
    CreatePostRequestDto dto = new CreatePostRequestDto(2L, "user 1 can get this post", PostPublicationState.PUBLIC);
    Long createdPostId = requestCreatePost(dto);

    // when, 팔로워 피드를 요청
    List<GetPostContentResponseDto> result = requestFeed(token);

    // then
    assertEquals(1, result.size());
    assertEquals(createdPostId, result.get(0).getId());
  }

  @Test
  void givenUserHasFollowerAndCreatePost_whenFollowerUserRequestFeedWithInvalidToken_thenFollowerCannotGetPostFromFeed() {
    // given
    CreatePostRequestDto dto = new CreatePostRequestDto(2L, "user 1 can get this post", PostPublicationState.PUBLIC);
    Long createdPostId = requestCreatePost(dto);

    // when, 팔로워 피드를 요청
    Integer code = requestFeedCode(token);

    // then
    assertEquals(400, code);
  }
}
