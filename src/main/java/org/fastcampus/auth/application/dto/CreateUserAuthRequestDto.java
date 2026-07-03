package org.fastcampus.auth.application.dto;

public record CreateUserAuthRequestDto(String email, String password, String Role, String name, String profileImageUrl) {
}
