package ej.airport.service;

import ej.airport.dto.AirportDetail;
import ej.airport.dto.ProviderDetail;
import ej.airport.entity.Airport;
import ej.airport.exception.NoEntryFoundException;
import ej.airport.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    public Airport getAirport(Long id) {
        return airportRepository.findById(id).orElseThrow(() -> new NoEntryFoundException("Not found airport by id: " + id));
    }

    public Page<AirportDetail> getAirportsByIsoCountry(String isoCountry, Pageable pageable) {
        return airportRepository.findByIsoCountry(isoCountry, pageable)
                .map(this::mapToAirportDetail);
    }

    public Page<AirportDetail> getAirportsByIsoCountryAndIsoRegion(String isoCountry, String isoRegion, Pageable pageable) {
        return airportRepository.findByIsoCountryAndIsoRegion(isoCountry, isoRegion, pageable)
                .map(this::mapToAirportDetail);
    }

    private AirportDetail mapToAirportDetail(Airport airport) {
        return new AirportDetail(
                airport.getName(),
                airport.getMunicipality(),
                mapToProviderDetail(airport)
        );
    }

    private ProviderDetail mapToProviderDetail(Airport airport) {
        if (airport.getProvider() == null) {
            return null;
        }

        return new ProviderDetail(
                airport.getProvider().getName(),
                airport.getProvider().getCurrency(),
                airport.getProvider().getPrice());
    }

}
