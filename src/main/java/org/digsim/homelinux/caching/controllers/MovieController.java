package org.digsim.homelinux.caching.controllers;

import org.digsim.homelinux.caching.businessDelegates.movies.Movie;
import org.digsim.homelinux.caching.services.movies.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */
@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	private static Logger LOG = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;


	//@RequestMapping(value="/",  method = RequestMethod.POST)
	@PostMapping(value = "/")
	public ResponseEntity<Movie> add(@RequestBody Movie input) {
		LOG.debug("Movie Title: {}", input.getTitle());
		List<Movie> existingMovie = movieService.findByTitle(input.getTitle());
		LOG.debug("Found the following movies: {}", existingMovie);
		if(existingMovie != null && !existingMovie.isEmpty()){
			throw new IllegalArgumentException("A Movie with this title already exists");
		}
		else
		{
			Movie movie = movieService.saveMovie(input);
			return ResponseEntity.ok(movie);
		}


	}

	//@RequestMapping(value="/highRated")
	@GetMapping(value="/highRated")
	public List<Movie> getHighRatedMovies(){
		Iterable<Movie> moviesIterable = movieService.findSixPlusMovies();
		List<Movie> movies = new ArrayList<>();
		moviesIterable.forEach(movies::add);
		return movies;
	}


	@RequestMapping(value="/")
	public List<Movie> getMovies(){
		Iterable<Movie> moviesIterable = movieService.findAll();
		List<Movie> movies = new ArrayList<>();
		moviesIterable.forEach(movies::add);
		return movies;
	}

	@RequestMapping(value="/{id}")
	public Movie getMovie(@PathVariable long id){
		Optional<Movie> movie =  movieService.findOne(id);
		return movie.orElseThrow( IllegalStateException::new );
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteMovie(@PathVariable long id){
		movieService.delete(id);
	}
}
