package ua.kiev.dimoon.dropwizard.hello.world.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan(value = {"ua.kiev.dimoon.dropwizard.hello.world.repositories"})
public class SpringConfiguration {
	
	@Autowired /*@Qualifier("dataSource")*/ DataSource dataSource;
		
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
}
