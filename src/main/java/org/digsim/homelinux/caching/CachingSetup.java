package org.digsim.homelinux.caching;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.stereotype.Component;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author AdNovum Informatik AG
 * Created on 04/01/18.
 */
@Component
public class CachingSetup implements JCacheManagerCustomizer
{
	@Override
	public void customize(CacheManager cacheManager)
	{
		cacheManager.createCache("actors", new MutableConfiguration<>()
				.setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(SECONDS, 5)))
				.setStoreByValue(false)
				.setStatisticsEnabled(true));
	}
}
