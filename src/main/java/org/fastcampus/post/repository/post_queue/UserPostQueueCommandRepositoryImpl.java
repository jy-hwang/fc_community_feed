package org.fastcampus.post.repository.post_queue;

import lombok.RequiredArgsConstructor;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.entity.post.UserPostQueueEntity;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.fastcampus.post.repository.jpa.JpaUserPostQueueRepository;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {
  private final JpaPostRepository jpaPostRepository;
  private final JpaUserRelationRepository jpaUserRelationRepository;
  private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

  @Override
  public void publishPost(PostEntity postEntity) {
    UserEntity userEntity = postEntity.getAuthor();
    List<Long> followerIds = jpaUserRelationRepository.findFollowers(userEntity.getId());

    List<UserPostQueueEntity> userPostQueueEntityList
        = followerIds.stream()
        .map(userId -> new UserPostQueueEntity(userId, postEntity.getId(), userEntity.getId()))
        .toList();

    jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
  }

  @Override
  public void saveFollowPost(Long userId, Long targetId) {

  }

  @Override
  public void deleteFollowPost(Long userId, Long targetId) {

  }
}
