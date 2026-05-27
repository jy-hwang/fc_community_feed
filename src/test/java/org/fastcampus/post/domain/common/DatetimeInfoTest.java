package org.fastcampus.post.domain.common;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatetimeInfoTest {
  @Test
  void givenCreated_whenUpdated_thenTimeAndStateAreUpdated() {
    // given
    DatetimeInfo datetimeInfo = new DatetimeInfo();
    LocalDateTime localDateTime = datetimeInfo.getDateTime();

    // when
    // 너무 빨리 동작해서 아예 같은 시간이 찍혀서 강제로 0.1초 딜레이를 줌
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    datetimeInfo.updateEditDatetime();

    // then
    assertTrue(datetimeInfo.isEdited());
    assertNotEquals(localDateTime, datetimeInfo.getDateTime());
  }
}
