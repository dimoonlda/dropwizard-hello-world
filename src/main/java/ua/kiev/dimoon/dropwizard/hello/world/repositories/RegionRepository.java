package ua.kiev.dimoon.dropwizard.hello.world.repositories;



import java.util.List;

import ua.kiev.dimoon.dropwizard.hello.world.entity.Region;

public interface RegionRepository {
    Region findByIdWithTerritories(Integer regionId);
    List<Region> findAll();
}
