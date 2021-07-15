package ej.airport.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "continent", nullable = false)
    private String continent;


}
