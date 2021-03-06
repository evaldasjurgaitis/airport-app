package ej.airport.service;

import ej.airport.feign.AirportPriceClient;
import ej.airport.model.Price;
import ej.airport.model.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final AirportPriceClient airportPriceClient;

    public Price getPrice(String provider, Long airportId) {
        return airportPriceClient.getPrice(provider, airportId);
    }

    public List<Provider> getProvidersWitPrice(List<String> providers, Long airportId) {
        List<Provider> providersWithPrice = new ArrayList<>();
        providers.forEach(providerName -> {
            Price price = getPrice(providerName, airportId);
            providersWithPrice.add(new Provider(providerName, price.getCurrency(), price.getValue(), airportId));
        });
        return providersWithPrice;
    }

}
