package ua.kiev.dimoon.dropwizard.hello.world.pool;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import com.codahale.metrics.MetricRegistry;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import io.dropwizard.db.ManagedDataSource;

public class ManagedHikariDataSource extends HikariDataSource implements ManagedDataSource{

	private final MetricRegistry metricRegistry;
	
	
	public ManagedHikariDataSource(HikariConfig hikariConfig, MetricRegistry metricRegistry) {
		super(hikariConfig);
		this.metricRegistry = metricRegistry;
	}

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException("Doesn't use java.util.logging");
    }

    @Override
    public void start() throws Exception {
    	this.setMetricRegistry(metricRegistry);
    }

    @Override
    public void stop() throws Exception {
        close();
    }
}
