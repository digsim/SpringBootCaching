package org.digsim.homelinux.caching.businessDelegates.actors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author AdNovum Informatik AG
 * Created on 03/01/18.
 */
@Component
public class SimpleActorsBD implements ActorsBD {

	@Override
	@Cacheable("actors")
	public Actor getByFirstname(String name) {
		simulateSlowService();
		Actor a = new Actor();
		a.setFirstname(name);
		a.setLastname("Ford");
		a.setId(5L);
		return a;
	}

	// Don't do this at home
	private void simulateSlowService() {
		try {
			long time = 3000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}
