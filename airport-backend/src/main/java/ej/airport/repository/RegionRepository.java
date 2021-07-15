package ej.airport.repository;

import ej.airport.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {

    List<Region> findByIsoCountry(String isoCountry);

}
