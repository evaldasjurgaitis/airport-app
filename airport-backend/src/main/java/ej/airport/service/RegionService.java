package ej.airport.service;

import ej.airport.entity.Region;
import ej.airport.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getAll() {
        return regionRepository.findAll();
    }

}
