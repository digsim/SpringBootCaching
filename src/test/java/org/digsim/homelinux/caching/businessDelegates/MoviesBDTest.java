package org.digsim.homelinux.caching.businessDelegates;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */

@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
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
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void findMovie() {
	}

	@Test
	void saveMovie() {
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
		// Executes all asserts, even if one is failing
		assertAll("Assert All of these", () -> assertEquals(movie.getTitle(), savedMovie.getTitle(), "Titles should be the same"), () -> assertEquals(movie.getiMDBRating(), savedMovie.getiMDBRating(), "IMDB Rating should be the same"));
		//JUnit 4 style
		//assertEquals("Titles should be the same", movie.getTitle(), savedMovie.getTitle());
		//assertEquals("IMDB Rating should be the same", Double.toString(movie.getiMDBRating()), Double.toString(savedMovie.getiMDBRating()));

	}
}