package org.digsim.homelinux.caching.businessDelegates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */
public class MoviesBD {
	private static Logger LOG = LoggerFactory.getLogger(MoviesBD.class);
	Map<String, Movie> movies = new HashMap<String, Movie>();

	public Movie findMovie(String id){
		return movies.get(id);
	}

	public Movie saveMovie(Movie movie){
		LOG.debug("Saving Movie");
		movies.put(movie.getKey(), movie);
		return movies.get(movie.getKey());
	}
}
