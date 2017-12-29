package ua.kiev.dimoon.dropwizard.hello.world.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ua.kiev.dimoon.dropwizard.hello.world.entity.Region;
import ua.kiev.dimoon.dropwizard.hello.world.repositories.RegionRepository;

@Path("/regions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegionResource {
	
	private RegionRepository regionRepository;

	public RegionResource(RegionRepository regionRepository) {
		this.regionRepository = regionRepository;
	}
	
	@Path("/{regionId}")
	@GET
	public Response getById(@PathParam("regionId") Integer regionId) {
		Region region = regionRepository.findByIdWithTerritories(regionId);
		if (null == region) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(region).build();
	}
}
