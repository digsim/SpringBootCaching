package org.digsim.homelinux.caching.businessDelegates;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class MoviesBDTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private MoviesBD movieBd;

	public MoviesBDTest() {
	}


	@Test
	public void findMovie() {
	}

	@Test
	public void saveMovie() {
		Movie movie = new Movie("From Dusk Till Dawn 2", 1981);
		movie.setiMDBRating(5.6);
		List<String> actors = new ArrayList<String>();
		actors.add("John Doe");
		actors.add("Jane Doe");
		movie.setActors(actors);
		entityManager.persist(movie);

		List<Movie> movies = movieBd.findByTitle("From Dusk Till Dawn 2");
		assertNotNull(movies, "Movie should not be Null");
		Movie savedMovie = movies.get(0);
		// Executes all asserts, even if one is failing -> only JUnit 5
		//assertAll("Assert All of these",
		//			() -> assertEquals(movie.getTitle(), savedMovie.getTitle(), "Titles should be the same"),
		//			() -> assertEquals(movie.getiMDBRating(), savedMovie.getiMDBRating(), "IMDB Rating should be the same"));
		//JUnit 4 style
		assertEquals("Titles should be the same", movie.getTitle(), savedMovie.getTitle());
		assertEquals("IMDB Rating should be the same", Double.toString(movie.getiMDBRating()), Double.toString(savedMovie.getiMDBRating()));

	}
}