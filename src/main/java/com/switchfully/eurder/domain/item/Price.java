package com.switchfully.eurder.domain.item;

import com.switchfully.eurder.infrastructure.utils.ValidationUtil;

public class Price {
    private final double amount;
    private final Currency currency;

    public Price(double amount, Currency currency) {
        ValidationUtil.throwExceptionIfNegativePrice(amount);
        this.amount = amount;
        ValidationUtil.throwExceptionIfNullObject(currency, "Currency");
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
