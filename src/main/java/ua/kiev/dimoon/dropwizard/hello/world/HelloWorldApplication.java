package ua.kiev.dimoon.dropwizard.hello.world;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import io.dropwizard.Application;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ua.kiev.dimoon.dropwizard.hello.world.config.HelloWorldConfiguration;
import ua.kiev.dimoon.dropwizard.hello.world.config.SpringConfiguration;
import ua.kiev.dimoon.dropwizard.hello.world.health.TemplateHealthCheck;
import ua.kiev.dimoon.dropwizard.hello.world.pool.HikariDataSourceFactory;
import ua.kiev.dimoon.dropwizard.hello.world.repositories.RegionRepository;
import ua.kiev.dimoon.dropwizard.hello.world.resources.HelloWorldResource;
import ua.kiev.dimoon.dropwizard.hello.world.resources.NotificationsResource;
import ua.kiev.dimoon.dropwizard.hello.world.resources.RegionResource;
import ua.kiev.dimoon.dropwizard.hello.world.spring.SpringContextLoaderListener;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
	public static void main(String[] args) throws Exception {
		new HelloWorldApplication().run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(HelloWorldConfiguration configuration, Environment environment) {
		
		HikariDataSourceFactory dataSourceFactory = configuration.getDataSourceFactory();
		ManagedDataSource dataSource = dataSourceFactory.build(environment.metrics(), "dataSource");
		
		final HelloWorldResource helloWorldResource = new HelloWorldResource(configuration.getTemplate(),
				configuration.getDefaultName());
		
		AnnotationConfigWebApplicationContext parent = new AnnotationConfigWebApplicationContext();
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

		parent.refresh();
		parent.getBeanFactory().registerSingleton("configuration", configuration);
		parent.getBeanFactory().registerSingleton("dataSource", dataSource);
		parent.registerShutdownHook();
		parent.start();

		// the real main app context has a link to the parent context
		ctx.setParent(parent);
		ctx.register(SpringConfiguration.class);
		ctx.refresh();
		ctx.registerShutdownHook();
		ctx.start();

		NotificationsResource notificationsResource = new NotificationsResource();
		final TemplateHealthCheck templateHealthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", templateHealthCheck);
		environment.jersey().register(helloWorldResource);
		environment.jersey().register(notificationsResource);
		environment.jersey().register(new RegionResource(ctx.getBean(RegionRepository.class)));
		
		//last, but not least, let's link Spring to the embedded Jetty in Dropwizard
		environment.servlets().addServletListeners(new SpringContextLoaderListener(ctx));
	}

}