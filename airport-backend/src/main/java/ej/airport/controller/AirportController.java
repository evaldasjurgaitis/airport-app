package ej.airport.controller;

import ej.airport.dto.AirportDetail;
import ej.airport.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping("/{id}")
    public AirportDetail get(@PathVariable Long id) {
        return airportService.getAirport(id);
    }

    @GetMapping
    public Page<AirportDetail> getAirportsByRequestParams(
            @RequestParam("isoCountry") String isoCountry,
            @RequestParam(name = "isoRegion", required = false) String isoRegion,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        if (isoRegion == null || isoRegion.isEmpty()) {
            return airportService.findByIsoCountryAndIsoRegion(isoCountry, pageable);
        }
        return airportService.findByIsoCountryAndIsoRegion(isoCountry, isoRegion, pageable);
    }

}
