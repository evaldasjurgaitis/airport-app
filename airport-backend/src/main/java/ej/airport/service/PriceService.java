package ej.airport.service;

import ej.airport.dto.Price;
import ej.airport.feign.AirportPriceFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final AirportPriceFeignClient airportPriceFeignClient;

    public Price getPrice(String provider, Long airportId) {
        return airportPriceFeignClient.getPrice(provider, airportId);
    }

    public List<Price> getPricesByProviders(List<String> providers, Long airportId) {
        List<Price> prices = new ArrayList<>();
        providers.forEach(provider -> {
            Price price = getPrice(provider, airportId);
            prices.add(price);
        });
        return prices;
    }

    public Price getLowestPriceByProviders(List<String> providers, Long airportId) {
        return getPricesByProviders(providers, airportId).stream()
                .min(Comparator.comparing(price -> Integer.parseInt(price.getValue())))
                .orElse(null);
    }

}
