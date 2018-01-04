package org.digsim.homelinux.caching;

import org.digsim.homelinux.caching.businessDelegates.actors.SimpleActorsBD;
import org.digsim.homelinux.caching.businessDelegates.movies.Movie;
import org.digsim.homelinux.caching.businessDelegates.movies.MoviesBD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

/**
 * Hello world!
 */
@Configuration
@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class App extends SpringBootServletInitializer {
	private static Logger LOG = LoggerFactory.getLogger(App.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}


	public static void main(String[] args) {
		LOG.info("Starting main method");
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(MoviesBD businessDelegate, SimpleActorsBD actorBd) {
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
			Optional<Movie> movie  = businessDelegate.findById(1L);
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

			LOG.info("Starting actor Toto");
			LOG.info("Name is {}", actorBd.getByFirstname("Toto"));

			LOG.info("Starting actor Harry");
			LOG.info("Name is {}", actorBd.getByFirstname("Harry"));

			LOG.info("Speed Starting actor Toto");
			LOG.info("Name is {}", actorBd.getByFirstname("Toto"));

			LOG.info("Starting actor Marry");
			LOG.info("Name is {}", actorBd.getByFirstname("Marry"));

			LOG.info("Starting actor Jane");
			LOG.info("Name is {}", actorBd.getByFirstname("Jane"));

			LOG.info("Starting actor Ford");
			LOG.info("Name is {}", actorBd.getByFirstname("Ford"));

			LOG.info("Starting actor Prefect");
			LOG.info("Name is {}", actorBd.getByFirstname("Prefect"));

			LOG.info("Slow Starting actor Toto");
			LOG.info("Name is {}", actorBd.getByFirstname("Toto"));
		};
	}
}
