package com.switchfully.eurder.domain.item;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void whenCreatingItemWithNullName_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Item(null, "f", new Price(10, Currency.EUR), 1));
    }

    @Test
    void whenCreatingItemWithBlankName_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Item("", "f", new Price(10, Currency.EUR), 1));
    }

    @Test
    void whenCreatingItemWithNullDescription_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Item("f", null, new Price(10, Currency.EUR), 1));
    }

    @Test
    void whenCreatingItemWithBlankDescription_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Item("f", "", new Price(10, Currency.EUR), 1));
    }

    @Test
    void whenCreatingItemWithNullPrice_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Item("f", "f", null, 1));
    }

    @Test
    void whenCreatingItemWithNegativeStock_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Item("f", "f", new Price(10, Currency.EUR), -10));
    }

    @Test
    void whenCreatingValidItemWith0Stock_thenThrowNoException() {
        assertDoesNotThrow(() -> new Item("f", "f", new Price(10, Currency.EUR), 0));
    }

    @Test
    void whenCreatingValidItemWithPositiveStock_thenThrowNoException() {
        assertDoesNotThrow(() -> new Item("f", "f", new Price(10, Currency.EUR), 10));
    }

}