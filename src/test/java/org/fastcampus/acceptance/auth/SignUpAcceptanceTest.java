package org.fastcampus.acceptance.auth;

import org.fastcampus.acceptance.utils.AcceptanceTestTemplate;
import org.fastcampus.auth.application.dto.SendEmailRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.fastcampus.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SignUpAcceptanceTest extends AcceptanceTestTemplate {
  private final String email ="email-user@example.com";

  @BeforeEach
  void setup() {
    this.cleanUp();
  }

  @Test
  void givenEmail_whenSendEmail_thenVerificationTokenSaved(){
    // given
    SendEmailRequestDto dto = new SendEmailRequestDto(email);

    // when
    Integer code = requestSendEmail(dto);

    // then
    String token = this.getEmailToken(email);
    assertNotNull(token);
    assertEquals(0, code);
  }
}
