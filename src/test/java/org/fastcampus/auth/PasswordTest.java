package org.fastcampus.auth;

import org.fastcampus.auth.domain.Password;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {
  @Test
  void givenPassword_whenMatchSamePassword_thenReturnTrue() {
    // given
    Password password = Password.createEncryptPassword("password");

    // when & then
    assertTrue(password.matchPassword("password"));
  }

  @Test
  void givenPassword_whenMatchDiffPassword_thenReturnFalse() {
    // given
    Password password = Password.createEncryptPassword("password1");

    // when & then
    assertFalse(password.matchPassword("password"));
  }

  @ParameterizedTest
  @NullAndEmptySource
  void givenPasswordIsNull_thenThrowError(String password) {
    assertThrows(IllegalArgumentException.class, () -> Password.createEncryptPassword(password));
  }

}
