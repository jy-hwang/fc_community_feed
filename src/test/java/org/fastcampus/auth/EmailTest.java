package org.fastcampus.auth;

import org.fastcampus.auth.domain.Email;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailTest {
  @ParameterizedTest
  @NullAndEmptySource
  void givenEmailIsEmpty_whenCreate_thenThrowError(String email) {
    assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
  }

  @ParameterizedTest
  @ValueSource(strings = {"valid/@ad", "naver.com", "natty#@naver", "안녕하세요.com"})
  void givenInvalidEmail_whenCreate_thenThrowError(String email) {
    assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
  }

  @ParameterizedTest
  @ValueSource(strings = {"valid@ad", "a@naver.com", "natty@naver", "test@test.com"})
  void givenValidEmail_whenCreate_thenReturnEmail(String email) {
    // given

    // when
    Email emailValue = Email.createEmail(email);

    // then
    assertEquals(email, emailValue.getEmailText());
  }
}
