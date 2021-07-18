package ej.airport.dto;

import ej.airport.entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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

    public static AirportDetail mapToAirportDetail(Airport airport) {
        return AirportDetail.builder().id(airport.getId())
                .id(airport.getId())
                .type(airport.getType())
                .name(airport.getName())
                .latitude(airport.getLatitude())
                .longitude(airport.getLongitude())
                .altitude(airport.getAltitude())
                .continent(airport.getContinent())
                .isoCountry(airport.getIsoCountry())
                .isoRegion(airport.getIsoRegion())
                .municipality(airport.getMunicipality())
                .sheduledService(airport.isSheduledService())
                .gpsCode(airport.getGpsCode())
                .iataCode(airport.getIataCode())
                .localCode(airport.getLocalCode())
                .availableProviders(airport.getProviders().stream()
                        .map(ProviderDetail::new)
                        .collect(Collectors.toList())
                )
                .build();
    }

}
