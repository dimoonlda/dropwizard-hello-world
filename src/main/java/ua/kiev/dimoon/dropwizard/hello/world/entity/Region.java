package ua.kiev.dimoon.dropwizard.hello.world.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

public class Region {
    private Integer regionId;
    private String regionDescription;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Set<Territory> territories;

    public Integer getRegionId() {
        return regionId;
    }

    public Region setRegionId(Integer regionId) {
        this.regionId = regionId;
        return this;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public Region setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
        return this;
    }

    public Set<Territory> getTerritories() {
        return territories;
    }

    public Region setTerritories(Set<Territory> territories) {
        this.territories = territories;
        return this;
    }
}
