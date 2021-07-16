package ej.airport.controller;

import ej.airport.entity.Airport;
import ej.airport.service.AirportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping("/{id}")
    public Airport getAirport(@PathVariable Long id) {
        return airportService.getAirport(id);
    }

    @GetMapping
    public Page<Airport> getAirports(
            @RequestParam String isoCountry,
            @RequestParam(required = false) String isoRegion,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        if (isoRegion == null || isoRegion.isEmpty()) {
            return airportService.getAirportsByIsoCountry(isoCountry, pageable);
        }
        return airportService.getAirportsByIsoCountryAndIsoRegion(isoCountry, isoRegion, pageable);
    }

}
