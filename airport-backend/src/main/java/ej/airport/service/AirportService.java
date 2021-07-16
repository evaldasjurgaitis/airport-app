package ej.airport.service;

import ej.airport.dto.AirportDto;
import ej.airport.dto.Price;
import ej.airport.entity.Airport;
import ej.airport.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
    private final PriceService priceService;

    public Airport getAirport(Long id) {
        return airportRepository.getById(id);
    }

    public Page<AirportDto> getAirportsByIsoCountry(String isoCountry, Pageable pageable) {
        Page<AirportDto> airportPage = airportRepository.findByIsoCountry(isoCountry, pageable)
                .map(airport -> {
                    Price price = priceService.getLowestPriceByProviders(airport.getAirportProviders(), airport.getId());
                    return new AirportDto(airport.getName(), airport.getMunicipality(), price);
                });
        return getSortedPageByPrice(airportPage);
    }

    public Page<AirportDto> getAirportsByIsoCountryAndIsoRegion(String isoCountry, String isoRegion, Pageable pageable) {
        Page<AirportDto> airportPage = airportRepository.findByIsoCountryAndIsoRegion(isoCountry, isoRegion, pageable)
                .map(airport -> {
                    Price price = priceService.getLowestPriceByProviders(airport.getAirportProviders(), airport.getId());
                    return new AirportDto(airport.getName(), airport.getMunicipality(), price);
                });
        return getSortedPageByPrice(airportPage);

    }

    private Page<AirportDto> getSortedPageByPrice(Page<AirportDto> airportPage) {
        List<AirportDto> sortedAirportsByPrice = airportPage.get()
                .sorted(Comparator.comparing(o -> Integer.parseInt(o.getPrice().getValue())))
                .collect(Collectors.toList());
        return new PageImpl<>(sortedAirportsByPrice, airportPage.getPageable(), airportPage.getTotalElements());
    }

}
