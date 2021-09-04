package com.forsrc.jredmine.server.config;

import java.lang.reflect.Method;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.forsrc.jredmine.server.model.Cacheable;

@Configuration
@Scope(scopeName = "singleton")
@EnableCaching()
public class EhcacheConfig extends CachingConfigurerSupport {

//    @Autowired
//    private CacheManager cacheManager;



//    @Bean
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager("jredmine", "jredmine/page");
//    }


//    @Bean
//    public CacheManager cacheManager() {
//        SimpleCacheManager cacheManager = new SimpleCacheManager();
//        cacheManager.setCaches(Arrays.asList(
//                new ConcurrentMapCache("jredmine"),
//                new ConcurrentMapCache("jredmine/page")));
//        return cacheManager;
//    }


    @Override
    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator() {
        // return new SimpleKeyGenerator();
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName()).append("/");
                if (params == null) {
                    System.out.println("[cache] ----> " + sb.toString());
                    return sb.toString();
                }
                for (Object obj : params) {
                    if(obj instanceof Cacheable) {
                        sb.append(((Cacheable) obj).getPk()).append("/");
                    } else {
                        sb.append(obj.toString()).append("/");
                    }
                }
                int length = sb.length();
                sb.delete(length - 1, length);
                System.out.println("[cache] ----> " + sb.toString());
                return sb.toString();
            }
        };
    }

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

//    @Autowired
//    private CacheManager cacheManager;
//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//
//
//
//            //Access cache instance by name
//            Cache cache = cacheManager.getCache("jredmine");
//
//            //Add entry to cache
//            cache.put("test", "test");
//
//            //Get entry from cache
//            System.out.println(cache.get("test").get());
//        };
//    }
}
