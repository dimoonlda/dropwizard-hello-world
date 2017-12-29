package ua.kiev.dimoon.dropwizard.hello.world.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseRepositoryImpl {

    @Autowired
    protected JdbcTemplate jdbcTemplate;
}
