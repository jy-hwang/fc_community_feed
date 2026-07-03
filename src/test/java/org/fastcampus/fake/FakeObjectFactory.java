package org.fastcampus.fake;

import org.fastcampus.post.application.CommentService;
import org.fastcampus.post.application.PostService;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.application.interfaces.LikeRepository;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.repository.FakeCommentRepository;
import org.fastcampus.post.repository.FakeLikeRepository;
import org.fastcampus.post.repository.FakePostRepository;
import org.fastcampus.user.application.UserRelationService;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepository;

public class FakeObjectFactory {
  private static final UserRepository userRepository = new FakeUserRepository();
  private static final UserService userService = new UserService(userRepository);
  private static final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
  private static final UserRelationService userRelationService = new UserRelationService(userService, userRelationRepository);
  private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();

  private static final PostRepository fakePostRepository = new FakePostRepository();
  private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
  private static final PostService postService = new PostService(userService, fakePostRepository, fakeLikeRepository);
  private static final CommentService commentService = new CommentService(fakeCommentRepository, userService, postService, fakeLikeRepository);

  private FakeObjectFactory() {
  }

  public static UserService getUserService() {

    return userService;
  }

  public static UserRelationService getUserRelationService() {

    return userRelationService;
  }

  public static LikeRepository getFakeLikeRepository() {

    return fakeLikeRepository;
  }

  public static PostService getPostService() {

    return postService;
  }

  public static CommentService getCommentService() {
    return commentService;
  }
}
