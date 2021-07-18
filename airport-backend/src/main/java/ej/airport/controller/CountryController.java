package ej.airport.controller;

import ej.airport.dto.CountryDetail;
import ej.airport.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public List<CountryDetail> getAll() {
        return countryService.getAll();
    }

}
