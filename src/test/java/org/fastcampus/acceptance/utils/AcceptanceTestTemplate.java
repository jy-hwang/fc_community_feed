package org.fastcampus.acceptance.utils;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AcceptanceTestTemplate {
  @Autowired
  private DatabaseCleanup cleanUp;

  @Autowired
  private DataLoader loader;

  public void init() {
    cleanUp.execute();
    loader.loadData();
  }

  protected void cleanUp() {
    cleanUp.execute();
  }

  protected String getEmailToken(String email) {
    return loader.getEmailToken(email);
  }

  protected boolean isEmailVerified(String email) {
    return loader.isEmailVerified(email);
  }

  protected Long getUserId(String email) {
    return loader.getUserId(email);
  }
}
