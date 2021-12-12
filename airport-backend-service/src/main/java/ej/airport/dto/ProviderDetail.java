package ej.airport.dto;

import ej.airport.entity.Provider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDetail {

    private String name;
    private String currency;
    private Integer price;

    public ProviderDetail(Provider provider) {
        if (provider == null) {
            return;
        }

        this.name = provider.getName();
        this.currency = provider.getCurrency();
        this.price = provider.getPrice();
    }

}
