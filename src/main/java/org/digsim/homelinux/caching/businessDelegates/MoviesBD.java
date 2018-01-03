package org.digsim.homelinux.caching.businessDelegates;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */
public interface MoviesBD extends CrudRepository<Movie, Long> {
	List<Movie> findByIMDBRating(Double IMDBRating);
	List<Movie> findByTitle(String title);
}
