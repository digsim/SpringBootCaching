package org.digsim.homelinux.caching.businessDelegates.actors;

import org.springframework.data.repository.Repository;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */
public interface ActorsBD extends Repository<Actor, Long> {

	public Actor getByFirstname(String Name);
}
