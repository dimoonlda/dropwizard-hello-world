package ua.kiev.dimoon.dropwizard.hello.world.resources;

import ua.kiev.dimoon.dropwizard.hello.world.entity.Notification;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/notifications")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotificationsResource {

    @GET
    @Path("/{id}")
    public Notification get(@PathParam("id") long id) {
        return new Notification().setMessage("ttttRRR tttt");
    }

    @POST
    public Response add(@Valid Notification notification) {
        final long id = 10001L;
        return Response.created(UriBuilder.fromPath("/notifications/{id}")
                .build(id))
                .build();
    }
}
