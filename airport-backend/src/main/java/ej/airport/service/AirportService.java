package ej.airport.service;

import ej.airport.entity.Airport;
import ej.airport.feign.AirportPriceFeignClient;
import ej.airport.repository.AirportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public Airport getAirport(Long id) {
        return airportRepository.getById(id);
    }

    public Page<Airport> getAirportsByIsoCountry(String isoCountry, Pageable pageable) {
        return airportRepository.findByIsoCountry(isoCountry, pageable);
    }

    public Page<Airport> getAirportsByIsoCountryAndIsoRegion(String isoCountry, String isoRegion, Pageable pageable) {
        return airportRepository.findByIsoCountryAndIsoRegion(isoCountry, isoRegion, pageable);
    }

}
