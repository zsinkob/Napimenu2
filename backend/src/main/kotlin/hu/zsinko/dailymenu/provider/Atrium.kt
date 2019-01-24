package hu.zsinko.dailymenu.provider

import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class Atrium(private val facebookService: FacebookService) : MenuProvider {

    private val restaurant = Restaurant("Átrium Étterem", "https://www.facebook.com/Átrium-Étterem-220584624687096/")
    private val dateFormat = DateTimeFormatter.ofPattern("MM.dd.")

    override fun getMenu(date: LocalDate): Menu {
        val post = facebookService.getPost("220584624687096") {
            it.message != null &&
                    Regex(dateFormat.format(date)).containsMatchIn(it.message)
        }

        val menu = post?.message ?: ""

        return generateMenu(menu, restaurant)
    }
}