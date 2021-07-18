package ej.airport.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    @Id
    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "continent")
    private String continent;

}
