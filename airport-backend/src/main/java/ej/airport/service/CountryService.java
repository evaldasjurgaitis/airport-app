package ej.airport.service;

import ej.airport.dto.CountryDetail;
import ej.airport.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public List<CountryDetail> getAll() {
        return countryRepository.findAllByOrderByName().stream()
                .map(country -> new CountryDetail(country.getCode(), country.getName()))
                .collect(Collectors.toList());
    }

}
