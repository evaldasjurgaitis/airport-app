package ej.airport.step.Airport;

import ej.airport.model.Airport;
import ej.airport.model.ModifyAirport;
import ej.airport.model.Provider;
import ej.airport.service.PriceService;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

public class Processor implements ItemProcessor<Airport, ModifyAirport> {

    private PriceService priceService;

    public Processor(PriceService priceService) {
        this.priceService = priceService;
    }

    public ModifyAirport process(Airport airport) {
        List<Provider> providers = priceService.getProvidersWitPrice(airport.getAvailableProviders(), airport.getId());
        return mapToModifyAirport(airport, providers);
    }

    private ModifyAirport mapToModifyAirport(Airport airport, List<Provider> providers) {
        return ModifyAirport.builder()
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
                .providers(providers)
                .build();
    }

}