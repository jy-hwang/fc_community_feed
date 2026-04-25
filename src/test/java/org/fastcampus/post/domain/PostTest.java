package org.fastcampus.post.domain;

import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostTest {
  private final UserInfo userInfo = new UserInfo("test", "url");
  private final User user1 = new User(1L, userInfo);
  private final User user2 = new User(2L, userInfo);

  private final PostContent postContent1 = new PostContent("content1111");
  private final Post post1 = new Post(1L, user1, postContent1);

  @Test
  void givenPostCreated_whenLike_thenLikeCountIsShouldBe1() {
    // when
    post1.like(user2);

    // then
    assertEquals(1, post1.getLikeCounter());
  }

  @Test
  void givenPostCreated_whenLikeBySelf_thenThrowError() {
    // when & then
    assertThrows(IllegalArgumentException.class, () -> post1.like(user1));
  }

  @Test
  void givenPostCreatedAndLike_whenUnlike_thenLikeCountIsShouldBe0() {
    // given
    post1.like(user2);

    // when
    post1.unlike();

    // then
    assertEquals(0, post1.getLikeCounter());
  }

  @Test
  void givenPostCreated_whenUnlike_thenLikeCountIsShouldBe0() {
    // when
    post1.unlike();

    // then
    assertEquals(0, post1.getLikeCounter());
  }
}
