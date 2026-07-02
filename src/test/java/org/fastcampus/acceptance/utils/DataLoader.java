package org.fastcampus.acceptance.utils;

import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

import static org.fastcampus.acceptance.steps.UserAcceptanceSteps.createUser;
import static org.fastcampus.acceptance.steps.UserAcceptanceSteps.followUser;

@Component
public class DataLoader {
  public void loadData() {
    createUser(new CreateUserRequestDto("test user1", ""));
    createUser(new CreateUserRequestDto("test user2", ""));
    createUser(new CreateUserRequestDto("test user3", ""));

    followUser(new FollowUserRequestDto(1L, 2L));
    followUser(new FollowUserRequestDto(1L, 3L));
  }
}
