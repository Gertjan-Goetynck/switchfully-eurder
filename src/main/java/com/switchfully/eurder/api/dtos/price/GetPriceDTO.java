package com.switchfully.eurder.api.dtos.price;

import com.switchfully.eurder.domain.item.Currency;

public class GetPriceDTO {
    private final double amount;
    private final Currency currency;

    public GetPriceDTO(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
