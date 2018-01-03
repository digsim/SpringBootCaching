package org.digsim.homelinux.caching.businessDelegates;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@DisplayName("Testing Movie BusinessDelegate")
class MoviesBDTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private MoviesBD movieBd;

	@BeforeAll
	public static void before() {
	}

	@BeforeEach
	void setUp() {
		//movieBd = new MoviesBD();
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void findMovie() {
	}

	@Test
	void saveMovie() {
		Movie movie = new Movie("From Dusk Till Dawn", 1981);
		movie.setiMDBRating(5.6);
		List<String> actors = new ArrayList<String>();
		actors.add("John Doe");
		actors.add("Jane Doe");
		movie.setActors(actors);
		entityManager.persist(movie);
		//Movie savedMovie = movieBd.saveMovie(movie);
		List<Movie> movies = movieBd.findByTitle("From Dusk Till Dawn");
		assertNotNull(movies, "Movie should not be Null");
		Movie savedMovie = movies.get(0);
		// Executes all asserts, even if one is failing
		assertAll("Assert All of these",
				() -> assertEquals(movie.getTitle(), savedMovie.getTitle(), "Titles should be the same"),
				() -> assertEquals(movie.getiMDBRating(), savedMovie.getiMDBRating(), "IMDB Rating should be the same"));
		//assertEquals(movie.getTitle(), savedMovie.getTitle(), "Titles should be the same");
		//assertEquals(movie.getIMDRating(), savedMovie.getIMDRating(), "IMDB Rating should be the same");

	}
}