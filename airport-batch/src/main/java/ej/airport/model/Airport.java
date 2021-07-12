package ej.airport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airport {

    private Long id;
    private String type;
    private String name;
    private Double latitude;
    private Double longitude;
    private Integer altitude;
    private String continent;
    private String isoCountry;
    private String isoRegion;
    private String municipality;
    private boolean sheduledService;
    private String gpsCode;
    private String iataCode;
    private String localCode;
    private boolean wizzAir;
    private boolean ryanAir;
    private boolean airBaltic;
    private boolean lufthansa;
    private boolean turkishAirLines;

}
