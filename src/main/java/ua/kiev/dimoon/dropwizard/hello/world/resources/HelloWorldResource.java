package ua.kiev.dimoon.dropwizard.hello.world.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.jersey.caching.CacheControl;
import ua.kiev.dimoon.dropwizard.hello.world.entity.Saying;
import ua.kiev.dimoon.dropwizard.hello.world.exceptions.HelloWorldException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    @CacheControl(maxAge = 6, maxAgeUnit = TimeUnit.HOURS)
    public Response sayHello(@QueryParam("name") Optional<String> name, @Context Request request) {
        final String value = String.format(template, name.orElse(defaultName));
        Response.ResponseBuilder rb = request.evaluatePreconditions(new EntityTag("22"));
        if (null != rb) {
            return rb.build();
        }
        return Response.ok(new Saying(counter.incrementAndGet(), value)).build();
    }

    @GET
    @Path("/exception")
    public Response sayException() {
        throw new HelloWorldException();
    }
}
