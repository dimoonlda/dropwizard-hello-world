package ua.kiev.dimoon.dropwizard.hello.world.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.kiev.dimoon.dropwizard.hello.world.entity.Region;
import ua.kiev.dimoon.dropwizard.hello.world.entity.Territory;

import java.util.HashSet;
import java.util.List;

@Repository
public class RegionRepositoryImpl extends BaseRepositoryImpl implements RegionRepository {

    RowMapper<Territory> territoryRowMapper = BeanPropertyRowMapper.newInstance(Territory.class);
    RowMapper<Region> regionRowMapper = BeanPropertyRowMapper.newInstance(Region.class);

    @Override
    public Region findByIdWithTerritories(Integer regionId) {
        return jdbcTemplate.query(
                "select * from region r, territories t where t.regionid=r.regionid and r.regionid=?",
                new Object[]{regionId},
                rs -> {
                    Region region = null;
                    int rowNum = 0;
                    while (rs.next()) {
                        if (null == region) {
                            region = regionRowMapper.mapRow(rs, rowNum).setTerritories(new HashSet<>());
                        }
                        region.getTerritories().add(
                                territoryRowMapper.mapRow(rs, rowNum++)
                        );
                    }
                    return region;
                }
        );
    }

    @Override
    public List<Region> findAll() {
        return jdbcTemplate.query("select * from region", regionRowMapper);
    }
}
