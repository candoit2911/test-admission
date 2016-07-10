package com.example.admission.service;

import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOWED_HEADERS_PARAM;
import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOWED_METHODS_PARAM;
import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOWED_ORIGINS_PARAM;
import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOW_CREDENTIALS_PARAM;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.example.admission.db.MongoDBService;
import com.example.admission.resource.AdmissionResource;
import com.mongodb.Mongo;


public class AdmissionApplicationDelpoy extends
		Application<AdmissionConfiguration> {
	public static void main(String[] args) throws Exception {
		new AdmissionApplicationDelpoy().run(args);
	}

	@Override
	public void run(AdmissionConfiguration configuration,
			Environment environment) throws Exception {

		FilterRegistration.Dynamic filter = environment.servlets().addFilter(
				"CORSFilter", CrossOriginFilter.class);

		filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),
				false, environment.getApplicationContext().getContextPath()
						+ "*");
		filter.setInitParameter(ALLOWED_METHODS_PARAM, "GET,PUT,POST,OPTIONS");
		filter.setInitParameter(ALLOWED_ORIGINS_PARAM, "*");
		filter.setInitParameter(ALLOWED_HEADERS_PARAM,
				"Origin, Content-Type, Accept");
		filter.setInitParameter(ALLOW_CREDENTIALS_PARAM, "true");

		Mongo mongo = new Mongo(configuration.mongohost,
				configuration.mongoport);
		MongoDBService mongoDBService = new MongoDBService(
				mongo.getDB(configuration.mongodb));
		AdmissionApplicationService service = new AdmissionApplicationService(
				mongoDBService, configuration);
		environment.jersey().register(new AdmissionResource(service));
	}

}
