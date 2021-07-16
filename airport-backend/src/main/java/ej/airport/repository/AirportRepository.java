package ej.airport.repository;

import ej.airport.entity.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {

//    List<Airport> findByIsoCountry(String isoCountry);
//
//    List<Airport> findByIsoCountryAndIsoRegion(String isoCountry, String isoRegion);

    Page<Airport> findByIsoCountry(String isoCountry, Pageable pageable);

    Page<Airport> findByIsoCountryAndIsoRegion(String isoCountry, String isoRegion, Pageable pageable);

}
