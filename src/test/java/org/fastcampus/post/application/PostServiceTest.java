package org.fastcampus.post.application;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.domain.User;

public class PostServiceTest {
  private final UserService userService = FakeObjectFactory.getUserService();
  private final PostService postService = FakeObjectFactory.getPostService();

  private final User user = userService.createUser(new CreateUserRequestDto("user1", null));
  private final User otherUser = userService.createUser(new CreateUserRequestDto("user1", null));

  private CreatePostRequestDto dto = new CreatePostRequestDto(user.getId(), "this is test Content", PostPublicationState.PUBLIC);
}
