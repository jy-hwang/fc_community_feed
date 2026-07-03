package org.fastcampus.auth.repository;

import lombok.extern.slf4j.Slf4j;
import org.fastcampus.auth.application.interfaces.EmailSendRepository;
import org.fastcampus.auth.domain.Email;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class EmailSendRepositoryImpl implements EmailSendRepository {
  @Override
  public void sendEmail(Email email, String token) {
    // TODO
    log.info("{} 으로 인증 메일을 보냈습니다.", email.getEmailText());
  }
}
