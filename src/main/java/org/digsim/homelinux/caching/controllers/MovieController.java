package org.digsim.homelinux.caching.controllers;

import org.digsim.homelinux.caching.businessDelegates.Movie;
import org.digsim.homelinux.caching.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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


	@RequestMapping(value="/",  method = RequestMethod.POST)
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

	@RequestMapping(value="/highRated")
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
		return movieService.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteMovie(@PathVariable long id){
		movieService.delete(id);
	}
}