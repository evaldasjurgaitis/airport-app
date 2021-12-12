package ej.airport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getAvailableProviders() {
        List<String> airportProviders = new ArrayList<>();
        if (wizzAir) {
            airportProviders.add(ProviderType.WIZZAIR.toString().toLowerCase());
        }
        if (ryanAir) {
            airportProviders.add(ProviderType.RYANAIR.toString().toLowerCase());
        }
        if (airBaltic) {
            airportProviders.add(ProviderType.AIRBALTIC.toString().toLowerCase());
        }
        if (lufthansa) {
            airportProviders.add(ProviderType.LUFTHANSA.toString().toLowerCase());
        }
        if (turkishAirLines) {
            airportProviders.add(ProviderType.TURKISHAIRLINES.toString().toLowerCase());
        }
        return airportProviders;
    }

}
