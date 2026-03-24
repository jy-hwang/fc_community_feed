package org.fastcampus.user.domain;

import java.util.Objects;

public class User {
  private final Long id;
  private final UserInfo info;
  private final UserRelationCounter followingCount;
  private final UserRelationCounter followerCount;

  public User(Long id, UserInfo userInfo) {
    this.id = id;
    this.info = userInfo;
    this.followingCount = new UserRelationCounter();
    this.followerCount = new UserRelationCounter();
  }

  public void follow(User targetUser) {
    if (targetUser.equals(this)) {
      throw new IllegalArgumentException();
    }

    followingCount.increase();
    //targetUser.followerCount.increase();
    targetUser.increaseFollowerCount();
  }

  public void unfollow(User targetUser) {
    if (targetUser.equals(this)) {
      throw new IllegalArgumentException();
    }

    followingCount.decrease();
    //targetUser.followerCount.decrease();
    targetUser.decreaseFollowerCount();
  }

  private void increaseFollowerCount() {
    followerCount.increase();
  }

  private void decreaseFollowerCount() {
    followerCount.decrease();
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
}
