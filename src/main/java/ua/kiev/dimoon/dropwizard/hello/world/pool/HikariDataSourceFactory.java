package ua.kiev.dimoon.dropwizard.hello.world.pool;

import com.codahale.metrics.MetricRegistry;
import com.zaxxer.hikari.HikariConfig;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.ManagedDataSource;

public class HikariDataSourceFactory extends DataSourceFactory{
	
	@Override
	public ManagedDataSource build(MetricRegistry metricRegistry, String name) {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(getDriverClass());	//"org.postgresql.Driver"
		hikariConfig.setJdbcUrl(getUrl());	//"jdbc:postgresql://localhost:5432/northwind" 
	    hikariConfig.setUsername(getUser());	//"postgres"
	    hikariConfig.setPassword(getPassword());	//"postgres"

	    hikariConfig.setMaximumPoolSize(getMaxSize());	//5
	    hikariConfig.setMinimumIdle(1);
	    hikariConfig.setConnectionTestQuery("SELECT 1");
	    hikariConfig.setPoolName(name);
		return new ManagedHikariDataSource(hikariConfig, metricRegistry);
	}
	
}
