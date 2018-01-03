package org.digsim.homelinux.caching.businessDelegates;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */
public class MoviesBD {
	Map<String, Movie> movies = new HashMap<String, Movie>();

	public Movie findMovie(String id){
		return movies.get(id);
	}

	public Movie saveMovie(Movie movie){
		movies.put(movie.getKey(), movie);
		return movies.get(movie.getKey());
	}
}
