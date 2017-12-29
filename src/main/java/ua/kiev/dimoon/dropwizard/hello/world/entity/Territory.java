package ua.kiev.dimoon.dropwizard.hello.world.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Territory {
    private Integer territoryId;
    private String territoryDescription;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Region region;

    public Integer getTerritoryId() {
        return territoryId;
    }

    public Territory setTerritoryId(Integer territoryId) {
        this.territoryId = territoryId;
        return this;
    }

    public String getTerritoryDescription() {
        return territoryDescription;
    }

    public Territory setTerritoryDescription(String territoryDescription) {
        this.territoryDescription = territoryDescription;
        return this;
    }

    public Region getRegion() {
        return region;
    }

    public Territory setRegion(Region region) {
        this.region = region;
        return this;
    }
}
