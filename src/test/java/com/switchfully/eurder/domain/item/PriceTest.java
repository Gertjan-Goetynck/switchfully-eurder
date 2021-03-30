package com.switchfully.eurder.domain.item;

import com.switchfully.eurder.infrastructure.exceptions.IllegalPriceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @Test
    void givenNegativePriceAmount_whenCreatingPrice_throwIllegalPriceException() {
        assertThrows(IllegalPriceException.class, () -> new Price(-1, Currency.EUR));
    }

    @Test
    void givenNullCurrency_whenCreatingPrice_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Price(0, null));
    }

    @Test
    void givenValidPrice_whenCreatingPrice_throwNoException() {
        assertDoesNotThrow(() -> new Price(100, Currency.EUR));
    }
}