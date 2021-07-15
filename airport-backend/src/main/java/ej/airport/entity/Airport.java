package ej.airport.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airports")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airport {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "altitude", nullable = false)
    private Integer altitude;

    @Column(name = "continent", nullable = false)
    private String continent;

    @Column(name = "iso_country", nullable = false)
    private String isoCountry;

    @Column(name = "iso_region", nullable = false)
    private String isoRegion;

    @Column(name = "municipality", nullable = false)
    private String municipality;

    @Column(name = "sheduled_service", nullable = false)
    private boolean sheduledService;

    @Column(name = "gps_code", nullable = false)
    private String gpsCode;

    @Column(name = "iata_code", nullable = false)
    private String iataCode;

    @Column(name = "local_code", nullable = false)
    private String localCode;

    @Column(name = "wizz_air", nullable = false)
    private boolean wizzAir;

    @Column(name = "ryan_air", nullable = false)
    private boolean ryanAir;

    @Column(name = "air_baltic", nullable = false)
    private boolean airBaltic;

    @Column(name = "lufthansa", nullable = false)
    private boolean lufthansa;

    @Column(name = "turkish_airlines", nullable = false)
    private boolean turkishAirLines;

}
