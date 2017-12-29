package ua.kiev.dimoon.dropwizard.hello.world.resources;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;
import ua.kiev.dimoon.dropwizard.hello.world.entity.Saying;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class HelloWorldResourceTest {
    private final static String TEMPLATE = "Hello, %s!";
    private final static String DEFAULT_NAME = "Stranger";

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new HelloWorldResource(TEMPLATE, DEFAULT_NAME))
            .build();

    @Test
    public void testSayHello() {
        String name = "DiMoon";
        Response response = resources.client()
                .target("/hello-world")
                .queryParam("name", name)
                .request()
                .get(Response.class);
        Saying entity = response.readEntity(Saying.class);
        assertEquals(200, response.getStatus());
        assertEquals("Hello, DiMoon!", entity.getContent());
        assertEquals(1L, entity.getId());
    }
}
