package ej.airport.service;

import ej.airport.dto.AirportDetail;
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

    public AirportDetail getAirport(Long id) {
        return airportRepository.findById(id).map(AirportDetail::mapToAirportDetail).orElseThrow(() -> new NoEntryFoundException("Not found airport by id: " + id));
    }

    public Page<AirportDetail> getAirports(String isoCountry, String isoRegion, Pageable pageable) {
        if (isoRegion == null || isoRegion.isEmpty()) {
            return airportRepository.findByIsoCountry(isoCountry, pageable)
                    .map(AirportDetail::new);
        }
        return airportRepository.findByIsoCountryAndIsoRegion(isoCountry, isoRegion, pageable)
                .map(AirportDetail::new);
    }

}
