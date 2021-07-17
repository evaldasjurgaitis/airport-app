package ej.airport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirportDetail {

    private String name;
    private String municipality;
    private ProviderDetail providerDetail;

}
