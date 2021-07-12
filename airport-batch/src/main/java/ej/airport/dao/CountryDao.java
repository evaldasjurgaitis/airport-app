package ej.airport.dao;

import ej.airport.model.Country;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CountryDao extends JdbcDaoSupport {

    private final DataSource dataSource;

    public CountryDao(@Qualifier("airportDS") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public void insert(List<Country> countries) {
        String sql = "INSERT INTO countries " + "(code, name, continent) VALUES (?, ?, ?)";
        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Country country = countries.get(i);
                ps.setString(1, country.getCode());
                ps.setString(2, country.getName());
                ps.setString(3, country.getContinent());
            }

            public int getBatchSize() {
                return countries.size();
            }
        });
    }

}
