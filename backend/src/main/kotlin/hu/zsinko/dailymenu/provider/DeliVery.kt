package hu.zsinko.dailymenu.provider

import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDate

@Service
class DeliVery(private val facebookService: FacebookService) : MenuProvider {

    private val restaurant = Restaurant("DeliVery", "https://www.facebook.com/deliveryszendvics/")

    override fun getMenu(date: LocalDate): Menu {
        val day = hunDays[DayOfWeek.from(LocalDate.now())]!!
        val nextDay = hunDays[DayOfWeek.from(LocalDate.now().plusDays(1))]!!
        val post = facebookService.getPost("deliveryszendvics") {
            it.message != null &&
                    Regex("Heti men(.*)", RegexOption.IGNORE_CASE).containsMatchIn(it.message)
        }

        val menu = post?.description?.linesBetween(day, nextDay, false, fullIfEmpty = true) ?: ""
        return generateMenu(menu, restaurant)
    }
}