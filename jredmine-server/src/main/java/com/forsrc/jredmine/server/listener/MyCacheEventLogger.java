package com.forsrc.jredmine.server.listener;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyCacheEventLogger implements CacheEventListener<Object, Object> {

    private static final Logger LOG = LoggerFactory.getLogger(MyCacheEventLogger.class);

    @Override
    public void onEvent(CacheEvent cacheEvent) {
        LOG.info("[CACHE]\t{}\tKey = {}; New value = {}; Old value = {}", cacheEvent.getType(), cacheEvent.getKey(),
                cacheEvent.getNewValue(), cacheEvent.getOldValue());
    }
}
