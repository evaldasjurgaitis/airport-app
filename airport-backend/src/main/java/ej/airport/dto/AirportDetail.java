package ej.airport.dto;

import ej.airport.entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportDetail {

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
    private ProviderDetail providerDetail;
    private List<ProviderDetail> availableProviders;

    public AirportDetail(Long id, String name, String municipality, ProviderDetail providerDetail) {
        this.id = id;
        this.name = name;
        this.municipality = municipality;
        this.providerDetail = providerDetail;
    }

    public AirportDetail(Airport airport) {
        this(
                airport.getId(),
                airport.getName(),
                airport.getMunicipality(),
                new ProviderDetail(airport.getProvider())
        );
    }

}
