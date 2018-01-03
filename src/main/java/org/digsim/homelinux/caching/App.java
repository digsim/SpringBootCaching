package org.digsim.homelinux.caching;

import org.digsim.homelinux.caching.businessDelegates.Movie;
import org.digsim.homelinux.caching.businessDelegates.MoviesBD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
	private static Logger LOG = LoggerFactory.getLogger(App.class);
	private static MoviesBD moviesBd = new MoviesBD();

	public static void main(String[] args) {
		System.out.println("Hello World!");
		LOG.info("Starting main method");
		Movie movie = new Movie("From Dusk Till Dawn", 1981);
		movie.setIMDRating(5.6);
		List<String> actors = new ArrayList<>();
		actors.add("John Doe");
		actors.add("Jane Doe");
		movie.setActors(actors);
		Movie savedMovie = moviesBd.saveMovie(movie);
		LOG.info("Title of movie is: \"{}", savedMovie.getTitle());
		LOG.debug("IMDB Rating of movie is: \"{}", savedMovie.getIMDRating());
	}
}
