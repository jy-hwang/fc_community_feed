package org.fastcampus.user.domain;

public record UserInfo(String name, String profileImageUrl) {
  public UserInfo {
    if (name == null || name.isEmpty()) throw new IllegalArgumentException();

  }
}