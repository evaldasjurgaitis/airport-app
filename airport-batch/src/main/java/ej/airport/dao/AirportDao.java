package ej.airport.dao;

import ej.airport.model.ModifyAirport;
import ej.airport.model.Provider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class AirportDao extends JdbcDaoSupport {

    private final DataSource dataSource;

    public AirportDao(@Qualifier("airportDS") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public void insert(List<ModifyAirport> airports) {

        String airportSql = "INSERT INTO airport (id, type, name, latitude, longitude, altitude, continent, iso_country, iso_region, municipality, sheduled_service, gps_code, iata_code, local_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";
        getJdbcTemplate().batchUpdate(airportSql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ModifyAirport airport = airports.get(i);
                ps.setLong(1, airport.getId());
                ps.setString(2, airport.getType());
                ps.setString(3, airport.getName());
                ps.setDouble(4, airport.getLatitude());
                ps.setDouble(5, airport.getLongitude());
                setParam(ps, 6, airport.getAltitude());
                ps.setString(7, airport.getContinent());
                ps.setString(8, airport.getIsoCountry());
                ps.setString(9, airport.getIsoRegion());
                ps.setString(10, airport.getMunicipality());
                ps.setBoolean(11, airport.isSheduledService());
                ps.setString(12, airport.getGpsCode());
                ps.setString(13, airport.getIataCode());
                ps.setString(14, airport.getLocalCode());
            }

            @Override
            public int getBatchSize() {
                return airports.size();
            }
        });

        for (ModifyAirport modifyAirport :
                airports) {
            String providerSql = "INSERT INTO provider (name, currency, price, airport_id) values(?,?,?,?);";
            getJdbcTemplate().batchUpdate(providerSql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Provider provider = modifyAirport.getProviders().get(i);
                    ps.setString(1, provider.getName());
                    ps.setString(2, provider.getCurrency());
                    setParam(ps, 3, provider.getPrice());
                    ps.setLong(4, provider.getAirportId());
                }

                @Override
                public int getBatchSize() {
                    return modifyAirport.getProviders().size();
                }
            });
        }

    }

    static void setParam(PreparedStatement stmt, int paramIndex, Integer value) throws SQLException {
        if (value == null || value == 0) {
            stmt.setNull(paramIndex, Types.INTEGER);
        } else {
            stmt.setInt(paramIndex, value);
        }
    }

}
