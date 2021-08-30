package com.forsrc.jredmine.server.config;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;

@Configuration
@Scope(scopeName = "singleton")
@EnableCaching()
public class EhcacheConfig extends CachingConfigurerSupport {

//    @Autowired
//    private CacheManager cacheManager;

//    @Bean
//    public CacheManager cacheManager() {
//        return new EhCacheCacheManager(getEhCacheFactory().getObject());
//    }
//
//    @Bean
//    public EhCacheManagerFactoryBean getEhCacheFactory() {
//        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
//        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
//        factoryBean.setShared(true);
//        return factoryBean;
//    }

//    @Bean
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager("jredmine", "jredmine/page");
//    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("jredmine"),
                new ConcurrentMapCache("jredmine/page")));
        return cacheManager;
    }

//    @Bean
//    @Override
//    public KeyGenerator keyGenerator() {
//        // return new SimpleKeyGenerator();
//        return new KeyGenerator() {
//            @Override
//            public Object generate(Object target, Method method, Object... params) {
//                StringBuilder sb = new StringBuilder();
//                sb.append(target.getClass().getName()).append(".").append(method.getName());
//                if (params == null) {
//                    sb.append("()");
//                    System.out.println("[cache] ----> " + sb.toString());
//                    return sb.toString();
//                }
//                sb.append("(");
//                for (Object obj : params) {
//                    sb.append(obj.toString()).append(", ");
//                }
//                int length = sb.length();
//                sb.delete(length - 2, length);
//                sb.append(")");
//                System.out.println("[cache] ----> " + sb.toString());
//                return sb.toString();
//            }
//        };
//    }

//    @Bean
//    @Override
//    public CacheResolver cacheResolver() {
//        return new SimpleCacheResolver(cacheManager());
//    }
//
//    @Bean
//    @Override
//    public CacheErrorHandler errorHandler() {
//        return new SimpleCacheErrorHandler();
//    }

}
