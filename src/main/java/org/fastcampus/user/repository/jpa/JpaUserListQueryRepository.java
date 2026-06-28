package org.fastcampus.user.repository.jpa;

import org.fastcampus.user.application.dto.GetUserListResponseDto;
import org.fastcampus.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {
  @Query(value = " SELECT new org.fastcampus.user.application.dto.GetUserListResponseDto(ue.name, ue.profileImage) " +
      " FROM UserRelationEntity ure " +
      " INNER JOIN UserEntity ue" +
      " ON ure.followerUserId = ue.id" +
      " WHERE ure.followingUserId = :userId")
  List<GetUserListResponseDto> getFollowingUserList(Long userId);

  @Query(value = " SELECT new org.fastcampus.user.application.dto.GetUserListResponseDto(ue.name, ue.profileImage) " +
      " FROM UserRelationEntity ure " +
      " INNER JOIN UserEntity ue" +
      " ON ure.followingUserId = ue.id" +
      " WHERE ure.followerUserId = :userId")
  List<GetUserListResponseDto> getFollowerUserList(Long userId);
}
