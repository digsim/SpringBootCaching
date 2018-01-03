package org.digsim.homelinux.caching;

import org.digsim.homelinux.caching.businessDelegates.Movie;
import org.digsim.homelinux.caching.businessDelegates.MoviesBD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hello world!
 */
@Configuration
@SpringBootApplication
@EnableJpaRepositories
public class App {
	private static Logger LOG = LoggerFactory.getLogger(App.class);


	public static void main(String[] args) {
		LOG.info("Starting main method");
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(MoviesBD businessDelegate) {
		return (args) -> {
			// save a couple of movies
			businessDelegate.save(new Movie("From Dusk Till Dawn", 1981));
			businessDelegate.save(new Movie("24 hours", 1982));
			businessDelegate.save(new Movie("28 days later", 1983));
			businessDelegate.save(new Movie("David", 1984));
			businessDelegate.save(new Movie("Michelle", 1985));

			// fetch all movies
			LOG.info("Customers found with findAll():");
			LOG.info("-------------------------------");
			Iterable<Movie> moviesIterator = businessDelegate.findAll();
			moviesIterator.forEach(movie -> LOG.info(movie.toString()));
			LOG.info("");

			// fetch an individual movie by ID
			Movie movie  = businessDelegate.findOne(1L);
			LOG.info("Movie found with findOne(1L):");
			LOG.info("--------------------------------");
			LOG.info(movie.toString());
			LOG.info("");

			// fetch movie by title
			LOG.info("Customer found with findByLastName('28 days later'):");
			LOG.info("--------------------------------------------");
			for (Movie dusk : businessDelegate.findByTitle("28 days later")) {
				LOG.info(dusk.toString());
			}
			LOG.info("");
		};
	}
}
