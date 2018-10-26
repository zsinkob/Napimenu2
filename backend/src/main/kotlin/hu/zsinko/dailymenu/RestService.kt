package hu.zsinko.dailymenu

import hu.zsinko.dailymenu.provider.Menu
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate


@RestController
@RequestMapping("/api")
open class RestService @Autowired constructor(
        private val menuService: MenuService
){
    @GetMapping("/dailymenu")
    fun allMovies(@RequestParam(value = "date", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate?): List<Menu> = menuService.getAllMenus(date ?: LocalDate.now())
}