package hu.zsinko.dailymenu

import hu.zsinko.dailymenu.provider.Menu
import hu.zsinko.dailymenu.provider.MenuProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MenuService(@Autowired private val providers: List<MenuProvider>) {
    companion object {
        val logger = loggerFor<MenuService>()
    }
    @Cacheable("menus")
    fun getAllMenus(date: LocalDate): List<Menu> =  providers.map { it.getMenu(date) }.toList()

    @CacheEvict(allEntries = true, cacheNames = ["menus"])
    @Scheduled(fixedDelay = 5 * 1000)
    fun cacheEvict() {
        logger.info("Cache evicted")
    }
}