package ej.airport.dao;

import ej.airport.model.Region;
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
public class RegionDao extends JdbcDaoSupport {

    private final DataSource dataSource;

    public RegionDao(@Qualifier("airportDS") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public void insert(List<Region> regions) {
        String sql = "INSERT INTO regions " + "(id, code, name, continent, iso_country) VALUES (?, ?, ?, ?, ?)";
        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Region region = regions.get(i);
                ps.setLong(1, region.getId());
                ps.setString(2, region.getCode());
                ps.setString(3, region.getName());
                ps.setString(4, region.getContinent());
                ps.setString(5, region.getIsoCountry());
            }

            public int getBatchSize() {
                return regions.size();
            }
        });
    }

}