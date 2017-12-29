package ua.kiev.dimoon.dropwizard.hello.world.exceptions;

import javax.ws.rs.WebApplicationException;

public class HelloWorldException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	public HelloWorldException() {
        super("It is Hello World exception.");
    }
}
