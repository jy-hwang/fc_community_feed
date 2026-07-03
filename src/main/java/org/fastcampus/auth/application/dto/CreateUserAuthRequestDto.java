package org.fastcampus.auth.application.dto;

public record CreateUserAuthRequestDto(
//@formatter:off
  String email
, String password
, String role
, String name
, String profileImageUrl
//@formatter:on
) {
}
