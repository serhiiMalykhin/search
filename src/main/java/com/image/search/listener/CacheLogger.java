package com.image.search.listener;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

@Slf4j
public class CacheLogger implements CacheEventListener<Object, Object> {

    private static final String LOG_CACHE = "Key: {} | EventType: {} | Old value: {} | New value: {}";

    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        log.debug(LOG_CACHE,
                cacheEvent.getKey(), cacheEvent.getType(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
    }
}
