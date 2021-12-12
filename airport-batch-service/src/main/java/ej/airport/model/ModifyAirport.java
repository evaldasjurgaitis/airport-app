package ej.airport.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModifyAirport {

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
    private List<Provider> providers;

}
