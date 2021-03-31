package com.switchfully.eurder.api.dtos.mappers;

import com.switchfully.eurder.api.dtos.price.GetPriceDTO;
import com.switchfully.eurder.domain.item.Price;

public class PriceDtoMapper {
    public static GetPriceDTO mapPriceToGetPriceDto(Price price) {
        return new GetPriceDTO(price.getAmount(), price.getCurrency());
    }


}
