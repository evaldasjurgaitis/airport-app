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

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportDetail getAirport(Long id) {
        return airportRepository.findById(id).map(this::mapToAirportDetail).orElseThrow(() -> new NoEntryFoundException("Not found airport by id: " + id));
    }

    public Page<AirportDetail> getAirportsByIsoCountry(String isoCountry, Pageable pageable) {
        return airportRepository.findByIsoCountry(isoCountry, pageable)
                .map(AirportDetail::new);
    }

    public Page<AirportDetail> getAirportsByIsoCountryAndIsoRegion(String isoCountry, String isoRegion, Pageable pageable) {
        return airportRepository.findByIsoCountryAndIsoRegion(isoCountry, isoRegion, pageable)
                .map(AirportDetail::new);
    }

    private AirportDetail mapToAirportDetail(Airport airport) {
        return AirportDetail.builder().id(airport.getId())
                .id(airport.getId())
                .type(airport.getType())
                .name(airport.getName())
                .latitude(airport.getLatitude())
                .longitude(airport.getLongitude())
                .altitude(airport.getAltitude())
                .continent(airport.getContinent())
                .isoCountry(airport.getIsoCountry())
                .isoRegion(airport.getIsoRegion())
                .municipality(airport.getMunicipality())
                .sheduledService(airport.isSheduledService())
                .gpsCode(airport.getGpsCode())
                .iataCode(airport.getIataCode())
                .localCode(airport.getLocalCode())
                .availableProviders(airport.getProviders().stream().map(ProviderDetail::new).collect(Collectors.toList()))
                .build();
    }

}
