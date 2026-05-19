package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.application.dto.UpdatePostRequestDto;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;

public class PostService {

  private final UserService userService;
  private final PostRepository postRepository;

  public PostService(UserService userService, PostRepository postRepository) {
    this.userService = userService;
    this.postRepository = postRepository;
  }

  public Post getPost(Long id) {
    return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));
  }

  public Post createPost(CreatePostRequestDto dto) {
    User author = userService.getUser(dto.userId());
    Post post = Post.createPost(null, author, dto.content(), dto.state());

    return postRepository.save(post);
  }

  public Post updatePost(Long id, UpdatePostRequestDto dto) {
    Post post = getPost(id);
    User user = userService.getUser(dto.userId());

    post.updatePost(user, dto.content(), dto.state());
    return postRepository.save(post);
  }
}
