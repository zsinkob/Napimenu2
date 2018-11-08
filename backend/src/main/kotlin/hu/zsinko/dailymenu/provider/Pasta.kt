package hu.zsinko.dailymenu.provider

import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class Pasta(private val facebookService: FacebookService) : MenuProvider {

    private val restaurant = Restaurant("PASTA.", "https://www.facebook.com/pastapont/")

    override fun getMenu(date: LocalDate): Menu {
        val post = facebookService.getPost("pastapont") {
            it.message != null &&
                    Regex("Mai Menü(.*)${date.dayOfMonth}", RegexOption.IGNORE_CASE).containsMatchIn(it.message)
        }

        val menu = post?.message?.linesBetween("APÁLY", "TŰZOLTÓ", false) ?: ""
        return generateMenu(menu, restaurant)
    }
}