package org.fastcampus.post.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.post_queue.UserPostQueueQueryRepository;
import org.fastcampus.post.ui.dto.GetPostContentResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Profile("test")
@Repository
public class FakeUserPostQueryRepository implements UserPostQueueQueryRepository {
  private final FakeUserQueueRedisRepository fakeUserQueueRedisRepository;

  public FakeUserPostQueryRepository(FakeUserQueueRedisRepository fakeUserQueueRedisRepository) {
    this.fakeUserQueueRedisRepository = fakeUserQueueRedisRepository;
  }

  @Override
  public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
    List<PostEntity> postEntities = fakeUserQueueRedisRepository.getPostListByUserId(userId);
    List<GetPostContentResponseDto> result = new ArrayList<>();
    for (PostEntity postEntity : postEntities) {
      GetPostContentResponseDto res
          = GetPostContentResponseDto
          .builder()
          .id(postEntity.getId())
          .build();
      result.add(res);
    }
    return result;
  }
}
