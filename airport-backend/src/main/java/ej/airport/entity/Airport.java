package ej.airport.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "airport")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airport {

    @Id
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "altitude")
    private Integer altitude;

    @Column(name = "continent")
    private String continent;

    @Column(name = "iso_country")
    private String isoCountry;

    @Column(name = "iso_region")
    private String isoRegion;

    @Column(name = "municipality")
    private String municipality;

    @Column(name = "sheduled_service")
    private boolean sheduledService;

    @Column(name = "gps_code")
    private String gpsCode;

    @Column(name = "iata_code")
    private String iataCode;

    @Column(name = "local_code")
    private String localCode;

    @OneToMany(mappedBy = "airport")
    private List<Provider> providers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="(select p.id from provider p where p.airport_id = id ORDER BY p.price ASC LIMIT 1)", referencedColumnName="id"))
    })
    private Provider provider;

}
