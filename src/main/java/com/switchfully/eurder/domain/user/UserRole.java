package com.switchfully.eurder.domain.user;

import com.switchfully.eurder.security.Features;

import java.util.Arrays;
import java.util.List;

public enum UserRole {
    ADMIN(
            Features.VIEW_CUSTOMER,
            Features.ADD_ITEM,
            Features.UPDATE_ITEM,
            Features.VIEW_ORDERS,
            Features.VIEW_ALL_CUSTOMERS,
            Features.VIEW_SHIPMENT_DAY,
            Features.VIEW_ITEM_OVERVIEW,
            Features.VIEW_ORDER_HISTORY
    ),
    CUSTOMER(
            Features.ORDER_ITEM,
            Features.REDO_ORDER,
            Features.VIEW_ORDER_HISTORY
    );

    private final List<Features> features;

    UserRole(Features... permissions) {
        this.features = Arrays.asList(permissions);
    }

    public List<Features> getFeatures() {
        return features;
    }
}
