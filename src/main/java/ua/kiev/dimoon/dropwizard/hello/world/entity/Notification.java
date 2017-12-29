package ua.kiev.dimoon.dropwizard.hello.world.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Notification {
    private String message;

    @JsonProperty
    public String getMessage() {
        return message;
    }

    @JsonProperty
    public Notification setMessage(String message) {
        this.message = message;
        return this;
    }
}
