package org.fastcampus.acceptance.auth;

import org.fastcampus.auth.domain.RandomTokenGenerator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;


public class RandomTokenGeneratorTest {
  private static final Logger log = LoggerFactory.getLogger(RandomTokenGeneratorTest.class);

  @Test
  void whenGenerateToken_thenReturnTokenWithCorrectLength() {
    // when
    String token = RandomTokenGenerator.generateToken();

    // then
    assertNotNull(token);
    assertEquals(16, token.length());
  }

  @Test
  void whenGenerateToken_thenReturnTokenWithValidCharacters() {
    // when
    String token = RandomTokenGenerator.generateToken();

    // then
    assertNotNull(token);
    assertTrue(token.matches("[0-9A-Za-z]{16}"));
  }

  @Test
  void whenGenerateTokenMultipleTimes_thenReturnUniqueTokens() {
    // when
    String token1 = RandomTokenGenerator.generateToken();
    String token2 = RandomTokenGenerator.generateToken();
    log.info("generatedToken1 : {}", token1);
    log.info("generatedToken2 : {}", token2);

    // then
    assertNotNull(token1);
    assertNotNull(token2);
    assertNotEquals(token1, token2);
  }
}
