package org.fastcampus.common.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositiveIntegerCounterTest {
  @Test
  void givenCreated_whenIncrease_thenCountIsOne() {
    // given
    PositiveIntegerCounter counter = new PositiveIntegerCounter();
    // when
    counter.increase();
    // then
    assertEquals(1, counter.getCount());
  }

}
