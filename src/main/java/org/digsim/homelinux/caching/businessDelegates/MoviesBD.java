package org.digsim.homelinux.caching.businessDelegates;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */
public interface MoviesBD extends JpaRepository<Movie, Long> {
	List<Movie> findByIMDBRating(Double IMDBRating);
	List<Movie> findByTitle(String title);
}
