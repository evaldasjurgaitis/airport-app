package ej.airport.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "region")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Region {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "continent")
    private String continent;

    @Column(name = "iso_country")
    private String isoCountry;

}
