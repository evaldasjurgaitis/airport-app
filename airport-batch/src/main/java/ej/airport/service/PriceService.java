package ej.airport.service;

import ej.airport.feign.AirportPriceFeignClient;
import ej.airport.model.Price;
import ej.airport.model.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final AirportPriceFeignClient airportPriceFeignClient;

    public Price getPrice(String provider, Long airportId) {
        return airportPriceFeignClient.getPrice(provider, airportId);
    }

    public List<Provider> getProviderWitPrice(List<String> providers, Long airportId) {
        List<Provider> providersWithPrice = new ArrayList<>();
        providers.forEach(providerName -> {
            Price price = getPrice(providerName, airportId);
            providersWithPrice.add(new Provider(airportId, providerName, price.getCurrency(), price.getValue()));
        });
        return providersWithPrice;
    }

}
