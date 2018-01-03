package org.digsim.homelinux.caching.businessDelegates.movies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */
@Entity
public class Movie {
	private static Logger LOG = LoggerFactory.getLogger(Movie.class);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;

	@Column
	@ElementCollection(targetClass = String.class)
	private List<String> actors = new ArrayList<String>();

	private int year;

	private double iMDBRating;

	protected Movie() {

	}

	public Movie(String title, int year) {
		this.title = title;
		this.year = year;
	}

	@Override
	public String toString() {
		return String.format("Movie[id=%d, title='%s', imdb rating='%f'", id, title, iMDBRating);
	}

	public Long getId() {
		return id;
	}

	public String getKey() {
		return title + "_" + year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getActors() {
		return actors;
	}

	public void setActors(List<String> actors) {
		this.actors = actors;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getiMDBRating() {
		return iMDBRating;
	}

	public void setiMDBRating(double IMDRating) {
		this.iMDBRating = IMDRating;
	}
}
