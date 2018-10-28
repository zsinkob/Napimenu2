package hu.zsinko.dailymenu.provider

import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter



@Service
class DonRoberto(private val facebookService: FacebookService): MenuProvider {

    private val restaurant = Restaurant("Don Roberto", "Robert Károly körút")
    private val dateFormat = DateTimeFormatter.ofPattern("MM.dd.")

    override fun getMenu(date: LocalDate): Menu {
        val post = facebookService.getPost("DonRobertoPizzeria") {
            it.message != null &&
                    Regex(dateFormat.format(date)).containsMatchIn(it.message)
        }

        val menu = post?.message?.linesBetween("CLASSIC", "menü") ?: ""

        return generateMenu(menu, restaurant)
    }
}