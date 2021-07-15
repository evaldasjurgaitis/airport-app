package ej.airport.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "regions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Region {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "continent", nullable = false)
    private String continent;

    @Column(name = "iso_country", nullable = false)
    private String isoCountry;

}
