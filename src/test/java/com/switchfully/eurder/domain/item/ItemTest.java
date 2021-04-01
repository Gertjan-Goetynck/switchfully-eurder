package com.switchfully.eurder.domain.item;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    void givenItemWithStockBelow5_thenStockUrgencyIsHigh(int input) {
        //GIVEN
        Item item = new Item("f", "f", new Price(10, Currency.EUR), input);

        //THEN
        assertEquals(StockUrgency.STOCK_LOW, item.getStockUrgency());
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 6, 7, 8, 9})
    void givenItemWithStockBetween5And9_thenStockUrgencyIsHigh(int input) {
        //GIVEN
        Item item = new Item("f", "f", new Price(10, Currency.EUR), input);

        //THEN
        assertEquals(StockUrgency.STOCK_MEDIUM, item.getStockUrgency());
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 11, 14578})
    void givenItemWithStock10AndAbove_thenStockUrgencyIsHigh(int input) {
        //GIVEN
        Item item = new Item("f", "f", new Price(10, Currency.EUR), input);

        //THEN
        assertEquals(StockUrgency.STOCK_HIGH, item.getStockUrgency());
    }

    @Test
    void givenAnItemWithPositiveStock_whenSellingAmountLessThanInStock_thenStockIsReducedByAmountSold() {
        //GIVEN
        int baseStock = 100;
        Item item = new Item("f", "f", new Price(10, Currency.EUR), baseStock);
        int amountToSell = 10;

        //WHEN
        item.reduceStock(amountToSell);

        //THEN
        assertEquals(baseStock - amountToSell, item.getAmountInStock());
    }

    @Test
    void givenAnItemWithPositiveStock_whenSellingAmountMoreThanInStock_thenStockIs0() {
        //GIVEN
        int baseStock = 100;
        Item item = new Item("f", "f", new Price(10, Currency.EUR), baseStock);
        int amountToSell = 200;

        //WHEN
        item.reduceStock(amountToSell);

        //THEN
        assertEquals(0, item.getAmountInStock());
    }

    @Test
    void givenAnItemWithHighStock_whenSellingEnoughToReachMediumStock_thenStockUrgencyIsMedium() {
        //GIVEN
        int baseStock = 10;
        Item item = new Item("f", "f", new Price(10, Currency.EUR), baseStock);
        int amountToSell = 5;

        //WHEN
        item.reduceStock(amountToSell);

        //THEN
        assertEquals(StockUrgency.STOCK_MEDIUM, item.getStockUrgency());
    }

    @Test
    void givenAnItemWithMediumStock_whenSellingEnoughToReachLowStock_thenStockUrgencyIsLow() {
        //GIVEN
        int baseStock = 5;
        Item item = new Item("f", "f", new Price(10, Currency.EUR), baseStock);
        int amountToSell = 5;

        //WHEN
        item.reduceStock(amountToSell);

        //THEN
        assertEquals(StockUrgency.STOCK_LOW, item.getStockUrgency());
    }

}