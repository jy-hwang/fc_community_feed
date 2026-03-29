package org.fastcampus.user.domain;

import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoTest {
  @Test
  void givenNameAndProfileImage_whenCreated_thenThrowNothing(){
    // given
    String name = "name1";
    String profileImage = "";

    // when
    // then
    assertDoesNotThrow(() -> new UserInfo(name, profileImage));
  }

  @Test
  void givenBlankNameAndProfileImage_whenCreated_thenThrowError(){
    // given
    String name = "";
    String profileImage = "";

    // when
    // then
    assertThrows(IllegalArgumentException.class,() -> new UserInfo(name, profileImage));
  }
}
