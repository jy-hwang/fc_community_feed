package org.fastcampus.user.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
  private final UserInfo userInfo = new UserInfo("test", "");
  private final User user1 = new User(1L, userInfo);
  private final User user2 = new User(2L, userInfo);

  @Test
  void givenTwoUser_whenEquals_thenReturnFalse() {
    // when
    boolean result = user1.equals(user2);

    // then
    assertFalse(result);
  }

  @Test
  void givenTwoSameIdUser_whenEquals_thenReturnTrue() {
    // given
    User sameUser = new User(1L, userInfo);

    // when
    boolean isSame = user1.equals(sameUser);

    // then
    assertTrue(isSame);
  }

  @Test
  void givenTwoUser_whenHashCode_thenNotEquals() {
    // when
    int hashCode1 = user1.hashCode();
    int hashCode2 = user2.hashCode();

    // then
    assertNotEquals(hashCode1, hashCode2);
  }

  @Test
  void givenTwoSameIdUser_whenHashCode_thenEquals() {
    // given
    User sameUser = new User(1L, userInfo);

    // when
    int hashCode1 = user1.hashCode();
    int sameUserHashCode = sameUser.hashCode();

    // then
    assertEquals(hashCode1, sameUserHashCode);
  }
}
