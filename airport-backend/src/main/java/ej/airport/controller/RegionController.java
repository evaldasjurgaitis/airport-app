package ej.airport.controller;

import ej.airport.dto.RegionDetail;
import ej.airport.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;
    
    @GetMapping
    public List<RegionDetail> getRegionsByIsoCountry(@RequestParam("isoCountry") String isoCountry) {
        return regionService.findByIsoCountryOrderByName(isoCountry);
    }

}
