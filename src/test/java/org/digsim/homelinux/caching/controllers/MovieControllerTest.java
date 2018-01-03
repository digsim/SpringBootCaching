package org.digsim.homelinux.caching.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.digsim.homelinux.caching.businessDelegates.movies.Movie;
import org.digsim.homelinux.caching.businessDelegates.movies.MoviesBD;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */
@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DisplayName("Testing Movie BusinessDelegate")
public class MovieControllerTest {

	//Required to Generate JSON content from Java objects
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	//Required to delete the data added for tests.
	//Directly invoke the APIs interacting with the DB
	@Autowired
	private MoviesBD moviesBd;

	//Test RestTemplate to invoke the APIs.
	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testCreateMovieApi() throws JsonProcessingException {

		//Building the Request body data
		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("title", "This is a test");
		requestBody.put("iMDBRating", 6.7d);
		requestBody.put("year", 1983);
		requestBody.put("actors", Arrays.asList("Buenos Aires", "Cordoba", "La Plata"));
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		//Creating http entity object with request body and headers
		HttpEntity<String> httpEntity =
				new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

		//Invoking the API
		Map<String, Object> apiResponse =
				restTemplate.postForObject("http://localhost:8888/movies/", httpEntity, Map.class, Collections.EMPTY_MAP);

		assertNotNull(apiResponse);

		//Asserting the response of the API.
		String title = (String) apiResponse.get("title");
		assertNotNull(title);

		//Fetching the Book details directly from the DB to verify the API succeeded
		List<Movie> moviesFromDb = moviesBd.findByTitle("This is a test");
		assertNotNull(moviesFromDb, "There should be at least one movie");
		assertFalse(moviesFromDb.isEmpty(), "There should be at least one movie");
		Movie movie = moviesFromDb.get(0);

		assertEquals("This is a test", title, "Titles differ");
		assertEquals(title, movie.getTitle(), "Titles differ");
		assertEquals(apiResponse.get("year"), movie.getYear(), "Titles differ");
		//Delete the data added for testing
		moviesBd.deleteById(movie.getId());

	}
}
