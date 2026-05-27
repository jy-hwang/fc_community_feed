package org.fastcampus.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostContentTest {
  @Test
  void givenContentLengthIsOk_whenCreated_thenReturnTextContent() {
    // given
    String text = "this is a test";

    // when
    PostContent postContent = new PostContent(text);

    // then
    assertEquals(text, postContent.contentText);
  }

  @Test
  void givenContentLengthIsOver_whenCreated_thenThrowsError() {
    // given
    String content = "a".repeat(501);

    // when & then
    assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
  }

  @ParameterizedTest
  @ValueSource(strings = {"뷁, 닭, 긁, 삵, 슳"})
  void givenContentLengthIsOverAndKorean_whenCreated_thenThrowsError(String koreanWord) {
    // given
    String content = koreanWord.repeat(501);

    // when & then
    assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
  }

  @Test
  void givenContentLengthIsUnder_whenCreated_thenThrowsError() {
    // given
    String content = "a".repeat(4);

    // when & then
    assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
  }

  @ParameterizedTest
  @NullAndEmptySource
  void givenContentIsEmpty_whenCreated_thenThrowsError(String value) {
    // when & then
    assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
  }

  @Test
  void givenContentLengthIsOk_whenUpdated_thenNotThrowError() {
    // given
    String text = "this is a test content";
    PostContent postContent = new PostContent(text);

    // when & then
    postContent.updateContent("this is a updated content");
  }

  @Test
  void givenContentLengthIsOk_whenUpdated_thenReturnUpdatedContent() {
    // given
    String text = "this is a test content";
    PostContent postContent = new PostContent(text);

    // when
    String updatedContent = "this is a updated content";
    postContent.updateContent(updatedContent);

    // then
    assertEquals(updatedContent, postContent.contentText);
  }


  @Test
  void givenContentLengthIsOver_whenUpdated_thenThrowsError() {
    // given
    String text = "this is a test content";
    PostContent postContent = new PostContent(text);

    // when & then
    String value = "a".repeat(501);
    assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(value));
  }

  @ParameterizedTest
  @ValueSource(strings = {"뷁, 닭, 긁, 삵, 슳"})
  void givenContentLengthIsOverAndKorean_whenUpdated_thenThrowsError(String koreanWord) {
    // given
    String text = "this is a test content";
    PostContent postContent = new PostContent(text);

    // when & then
    String value = koreanWord.repeat(501);
    assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(value));
  }

  @Test
  void givenContentLengthIsUnder_whenUpdated_thenThrowsError() {
    // given
    String text = "this is a test content";
    PostContent postContent = new PostContent(text);

    // when & then
    String content = "a".repeat(4);
    assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
  }
}
