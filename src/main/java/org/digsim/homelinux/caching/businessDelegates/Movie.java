package org.digsim.homelinux.caching.businessDelegates;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */
public class Movie {

	private String title;
	private List<String> actors = new ArrayList<String>();
	private int year;
	private double IMDRating;

	public Movie(){

	}

	public Movie(String title, int year){
		this.title = title;
		this.year = year;
	}

	public String getKey(){
		return title+"_"+year;
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

	public double getIMDRating() {
		return IMDRating;
	}

	public void setIMDRating(double IMDRating) {
		this.IMDRating = IMDRating;
	}
}
