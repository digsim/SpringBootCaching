package org.digsim.homelinux.caching.services;

import org.digsim.homelinux.caching.businessDelegates.Movie;
import org.digsim.homelinux.caching.businessDelegates.MoviesBD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */
@Service
@Transactional
public class MovieServiceImpl implements MovieService {

	private static Logger LOG = LoggerFactory.getLogger(MovieService.class);

	@Autowired
	private MoviesBD moviesBd;

	@Override
	public Iterable<Movie> findAll() {
		return moviesBd.findAll();
	}

	@Override
	public Movie saveMovie(Movie movie) {
		return moviesBd.save(movie);
	}

	@Override
	public Optional<Movie> findOne(long id) {
		Assert.notNull(id, "Id can't be null");
		return moviesBd.findById(id);
	}

	@Override
	public List<Movie> findByTitle(String title) {
		Assert.notNull(title, "A title must be provided");
		Assert.isTrue(!title.isEmpty(), "Title can't be empty");
		return moviesBd.findByTitle(title);
	}

	@Override
	public List<Movie> findByIMDBRating(String rating) {
		Assert.notNull(rating, "A title must be provided");
		Assert.isTrue(!rating.isEmpty(), "Title can't be empty");
		try {
			Double parsedRating = Double.parseDouble(rating);
			return moviesBd.findByIMDBRating(parsedRating);
		}
		catch (NumberFormatException e) {
			LOG.error("Invalid rating", e);
			return null;
		}
	}

	@Override
	public List<Movie> findSixPlusMovies() {
		List<Movie> movies =  moviesBd.findHighRatedMovies(new Double(6));
		return movies;
	}

	@Override
	public void delete(long id) {
		moviesBd.deleteById(id);
	}
}
