package org.example.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


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

    @Test
    void giverCreatedAndIncreased_whenDecrease_thenCountIsZero() {
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        counter.increase();

        // when
        counter.decrease();

        // then
        assertEquals(0, counter.getCount());
    }

    @Test
    void giverCreated_whenDecrease_thenCountIsZero() {
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // when
        counter.decrease();

        // then
        assertEquals(0, counter.getCount());
    }
}
