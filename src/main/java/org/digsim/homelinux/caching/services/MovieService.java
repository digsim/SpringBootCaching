package org.digsim.homelinux.caching.services;

import org.digsim.homelinux.caching.businessDelegates.Movie;

import java.util.List;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */
public interface MovieService {

	public Iterable<Movie> findAll();
	public Movie saveMovie(Movie movie);
	public Movie findOne(long id);
	public List<Movie> findByTitle(String title);
	public List<Movie> findByIMDBRating(String rating);
	public List<Movie> findSixPlusMovies();
	public void delete(long id);
}
