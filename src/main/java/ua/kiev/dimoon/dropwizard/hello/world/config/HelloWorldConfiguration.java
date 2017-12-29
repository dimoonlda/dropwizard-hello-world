package ua.kiev.dimoon.dropwizard.hello.world.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import ua.kiev.dimoon.dropwizard.hello.world.pool.HikariDataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class HelloWorldConfiguration extends Configuration {
	@NotEmpty
	private String template;

	@NotEmpty
	private String defaultName = "Stranger";

	private HikariDataSourceFactory dataSourceFactory;

	@Valid
	@NotNull
	public HikariDataSourceFactory getDataSourceFactory() {
		return dataSourceFactory;
	}

	@JsonProperty("database")
	public void setDataSourceFactory(HikariDataSourceFactory dataSourceFactory) {
		this.dataSourceFactory = dataSourceFactory;
	}

	@JsonProperty
	public String getTemplate() {
		return template;
	}

	@JsonProperty
	public void setTemplate(String template) {
		this.template = template;
	}

	@JsonProperty
	public String getDefaultName() {
		return defaultName;
	}

	@JsonProperty
	public void setDefaultName(String name) {
		this.defaultName = name;
	}
}
