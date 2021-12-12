package ej.airport.repository;

import ej.airport.entity.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AirportRepository extends PagingAndSortingRepository<Airport, Long> {

    @Query("SELECT airport FROM Airport airport JOIN Provider p on  airport.provider.id = p.id where airport.isoCountry =?1 ORDER BY p.price ASC")
    Page<Airport> findByIsoCountry(String isoCountry, Pageable pageable);

    @Query("SELECT airport FROM Airport airport JOIN Provider p on  airport.provider.id = p.id where airport.isoCountry =?1 and airport.isoRegion =?2 ORDER BY p.price ASC")
    Page<Airport> findByIsoCountryAndIsoRegion(String isoCountry, String isoRegion, Pageable pageable);

}
