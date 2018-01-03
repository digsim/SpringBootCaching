package org.digsim.homelinux.caching.businessDelegates;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */
@DisplayName("Testing Movie BusinessDelegate")
class MoviesBDTest {

	private MoviesBD movieBd;

	@BeforeAll
	public static void before() {
	}

	@BeforeEach
	void setUp() {
		movieBd = new MoviesBD();
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
		movie.setIMDRating(5.6);
		List<String> actors = new ArrayList<String>();
		actors.add("John Doe");
		actors.add("Jane Doe");
		movie.setActors(actors);
		Movie savedMovie = movieBd.saveMovie(movie);

		assertNotNull(savedMovie, "Movie should not be Null");
		assertAll("Assert All of these",
				() -> assertEquals(movie.getTitle(), savedMovie.getTitle(), "Titles should be the same"),
				() -> assertEquals(movie.getIMDRating(), savedMovie.getIMDRating(), "IMDB Rating should be the same"));

	}
}