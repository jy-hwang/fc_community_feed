package org.fastcampus.auth.domain;

public class Password {
  private final String encryptedPassword;

  private Password(String encryptedPassword) {
    this.encryptedPassword = encryptedPassword;
  }

  public static Password createEncryptPassword(String password) {
    if (password == null || password.isEmpty()) {
      throw new IllegalArgumentException("비밀번호는 빈 값일 수 없습니다.");
    }

    return new Password(SHA256.encrypt(password));
  }

  public boolean matchPassword(String password) {
    return encryptedPassword.matches(SHA256.encrypt(password));
  }

  public String getEncryptedPassword() {
    return encryptedPassword;
  }
}
