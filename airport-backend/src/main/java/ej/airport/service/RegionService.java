package ej.airport.service;

import ej.airport.dto.RegionDetail;
import ej.airport.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<RegionDetail> findByIsoCountryOrderByName(String isoCountry) {
        return regionRepository.findByIsoCountryOrderByName(isoCountry).stream()
                .map(region -> new RegionDetail(region.getCode(), region.getName()))
                .collect(Collectors.toList());
    }

}
