package ej.airport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provider {

    String name;
    String currency;
    Integer price;
    Long airportId;

}
