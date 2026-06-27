package org.fastcampus.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.common.repository.entity.TimeBaseEntity;

import java.util.Objects;

@Getter
@AllArgsConstructor
@Builder
public class User extends TimeBaseEntity {
  private Long id;
  private UserInfo info;
  private PositiveIntegerCounter followingCounter;
  private PositiveIntegerCounter followerCounter;

  public User(Long id, UserInfo userInfo) {
    if (userInfo == null) {
      throw new IllegalArgumentException();
    }

    this.id = id;
    this.info = userInfo;
    this.followingCounter = new PositiveIntegerCounter();
    this.followerCounter = new PositiveIntegerCounter();
  }

  public void follow(User targetUser) {
    if (targetUser.equals(this)) {
      throw new IllegalArgumentException();
    }

    followingCounter.increase();
    //targetUser.followerCount.increase();
    targetUser.increaseFollowerCount();
  }

  public void unfollow(User targetUser) {
    if (targetUser.equals(this)) {
      throw new IllegalArgumentException();
    }

    followingCounter.decrease();
    //targetUser.followerCount.decrease();
    targetUser.decreaseFollowerCount();
  }

  private void increaseFollowerCount() {
    followerCounter.increase();
  }

  private void decreaseFollowerCount() {
    followerCounter.decrease();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  public int followerCount() {
    return followerCounter.getCount();
  }

  public int followingCount() {
    return followingCounter.getCount();
  }

  public String getProfileImage() {
    return info.profileImageUrl();
  }

  public String getName() {
    return info.name();
  }
}
