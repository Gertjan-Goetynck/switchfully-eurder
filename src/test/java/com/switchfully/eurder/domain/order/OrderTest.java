package com.switchfully.eurder.domain.order;

import com.switchfully.eurder.domain.item.Currency;
import com.switchfully.eurder.domain.item.Item;
import com.switchfully.eurder.domain.item.Price;
import com.switchfully.eurder.domain.order.itemgroup.ItemGroup;
import com.switchfully.eurder.domain.user.Customer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private final ItemGroup itemGroup = new ItemGroup(new Item("test", "test", new Price(10, Currency.EUR), 9999), 2);
    private final Customer customer = new Customer("F", "f", "f@f.f","f", "f", "F");

    @Test
    void givenNullItemGroup_whenCreatingOrder_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Order(null, customer));
    }

    @Test
    void givenNullCustomer_whenCreatingOrder_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Order(List.of(itemGroup), null));
    }

    @Test
    void givenValidOrderWithOneItemGroupWithTotalValue20_whenCalculatingTotalOrderPrice_returnPriceOf20() {
        //EXPECTED
        Price expected = new Price(20, Currency.EUR);


        //WHEN
        Order order = new Order(List.of(itemGroup), customer);

        //THEN
        assertEquals(expected.getAmount(), order.calculateOrderPrice().getAmount());
    }
}