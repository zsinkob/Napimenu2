package hu.zsinko.dailymenu

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.social.facebook.api.impl.FacebookTemplate
import java.util.concurrent.TimeUnit


@Configuration
@PropertySource("classpath:application.properties")
class Beans {
    @Bean
    fun facebookTemplate(@Value("\${menu.facebooktoken}") token: String): FacebookTemplate = FacebookTemplate(token)

    @Bean
    fun cacheManager(): CacheManager {
        val bookCache = CaffeineCache("menus", Caffeine.newBuilder()
                .expireAfterAccess(15, TimeUnit.MINUTES)
                .build())
        val cacheManager = SimpleCacheManager()
        cacheManager.setCaches(arrayListOf(bookCache))
        return cacheManager
    }
}