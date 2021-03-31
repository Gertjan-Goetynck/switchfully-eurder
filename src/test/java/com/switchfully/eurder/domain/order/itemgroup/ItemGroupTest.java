package com.switchfully.eurder.domain.order.itemgroup;

import com.switchfully.eurder.domain.item.Currency;
import com.switchfully.eurder.domain.item.Item;
import com.switchfully.eurder.domain.item.Price;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ItemGroupTest {

    @Test
    void givenOutOfStockItems_whenCreatingNewItemGroup_thenShippingDateIs7DaysAfterOrderDate() {
        ItemGroup itemGroup = new ItemGroup(new Item("Test","Test",new Price(10, Currency.EUR),0),10);

        assertEquals(LocalDate.now().plusDays(7),itemGroup.getShippingDate());
    }

    @Test
    void givenInStockButLessThanCustomerPurchase_whenCreatingNewItemGroup_thenShippingDateIs7DaysAfterOrderDate() {
        ItemGroup itemGroup = new ItemGroup(new Item("Test","Test",new Price(10, Currency.EUR),7),10);

        assertEquals(LocalDate.now().plusDays(7),itemGroup.getShippingDate());
    }

    @Test
    void givenInStockAndEnoughForCustomerPurchase_whenCreatingNewItemGroup_thenShippingDateIsDayAfterOrder() {
        ItemGroup itemGroup = new ItemGroup(new Item("Test","Test",new Price(10, Currency.EUR),50),10);

        assertEquals(LocalDate.now().plusDays(1),itemGroup.getShippingDate());
    }
}