package org.fastcampus.post.repository.entity.comment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.user.repository.entity.UserEntity;

@Entity
@Table(name = "community_comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "authorId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private UserEntity author;

  @ManyToOne
  @JoinColumn(name = "postId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private PostEntity post;

  private String content;
  private Integer likeCount;

  public CommentEntity(Comment comment) {
    this.id = comment.getId();
    this.author = new UserEntity(comment.getAuthor());
    this.post = new PostEntity(comment.getPost());
    this.content = comment.getContent();
    this.likeCount = comment.getLikeCounter();
  }

  public Comment toComment() {
    return Comment.builder()
        .id(id)
        .author(author.toUser())
        .post(post.toPost())
        .content(new CommentContent(content))
        .likeCounter(new PositiveIntegerCounter(likeCount))
        .build();
  }

}
