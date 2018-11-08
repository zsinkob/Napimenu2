package hu.zsinko.dailymenu.provider

import hu.zsinko.dailymenu.loggerFor
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDate


@Service
class Dzsango(private val facebookService: FacebookService) : MenuProvider {

    companion object {
        val logger = loggerFor<Dzsango>()
    }

    private val restaurant = Restaurant("Dzsángó", "https://www.facebook.com/Dzs%C3%A1ng%C3%B3-687556554630210/")

    private val days = hashMapOf(DayOfWeek.MONDAY to "Hétfő",
            DayOfWeek.TUESDAY to "Kedd",
            DayOfWeek.WEDNESDAY to "Szerda",
            DayOfWeek.THURSDAY to "Csütörtök",
            DayOfWeek.FRIDAY to "Péntek",
            DayOfWeek.SATURDAY to "Szombat"
    )

    override fun getMenu(date: LocalDate): Menu {
        val day = days[DayOfWeek.from(LocalDate.now())]!!
        logger.info("today is $day")
        val post = facebookService.getPost("687556554630210") {
            it.message != null &&
                    Regex("${day}.*leves", hashSetOf(RegexOption.IGNORE_CASE, RegexOption.DOT_MATCHES_ALL)).containsMatchIn(it.message)
        }

        val menu = post?.message?.linesBetween("$day", "Gyertek", false, true) ?: ""

        return generateMenu(menu, restaurant)
    }
}