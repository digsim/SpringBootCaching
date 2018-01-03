package org.digsim.homelinux.caching.businessDelegates.actors;

import org.digsim.homelinux.caching.businessDelegates.movies.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */
@Entity
public class Actor {
	private static Logger LOG = LoggerFactory.getLogger(Actor.class);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstname;
	private String lastname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String toString(){
		return String.format("Actor[id=%d, firstname='%s', lastname='%s'", id, firstname, lastname);
	}
}
