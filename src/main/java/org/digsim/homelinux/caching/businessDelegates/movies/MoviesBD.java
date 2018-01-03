package org.digsim.homelinux.caching.businessDelegates.movies;

import org.digsim.homelinux.caching.businessDelegates.movies.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */
public interface MoviesBD extends CrudRepository<Movie, Long> {
	List<Movie> findByIMDBRating(Double IMDBRating);
	List<Movie> findByTitle(String title);
	@Query(value = "select * from Movie m where m.imdbrating>?1", nativeQuery = true)
	List<Movie> findHighRatedMovies(Double ratingLimit);
}
