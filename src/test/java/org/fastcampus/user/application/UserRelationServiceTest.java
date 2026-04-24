package org.fastcampus.user.application;

import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepository;

public class UserRelationServiceTest {
  private final UserRepository userRepository = new FakeUserRepository();
  private final UserService userService = new UserService(userRepository);
  private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
  private final UserRelationService userRelationService = new UserRelationService(userService, userRelationRepository);

}
