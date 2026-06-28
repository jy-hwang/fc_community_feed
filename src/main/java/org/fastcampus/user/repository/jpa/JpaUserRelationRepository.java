package org.fastcampus.user.repository.jpa;

import org.fastcampus.user.repository.entity.UserRelationEntity;
import org.fastcampus.user.repository.entity.UserRelationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationId> {
@Query(" SELECT ure.followingUserId" +
    " FROM UserRelationEntity ure" +
    " WHERE ure.followerUserId = :userId")
  List<Long> findFollowers(Long userId);
}
