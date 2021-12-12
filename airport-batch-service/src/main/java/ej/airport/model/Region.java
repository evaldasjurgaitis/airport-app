package ej.airport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Region {

    private Long id;
    private String code;
    private String name;
    private String continent;
    private String isoCountry;

}


