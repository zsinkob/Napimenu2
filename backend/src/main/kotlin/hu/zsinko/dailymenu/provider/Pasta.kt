package hu.zsinko.dailymenu.provider

import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class Pasta(private val facebookService: FacebookService): MenuProvider {

    private val restaurant = Restaurant("PASTA.", "Iroda mögött")

    override fun getMenu(date: LocalDate): Menu {
        val post = facebookService.getPost("pastapont", Regex("Mai Menü(.*)${date.dayOfMonth}", RegexOption.IGNORE_CASE)::containsMatchIn)
        return generateMenu(post.linesBetween("APÁLY", "TŰZOLTÓ", false), restaurant)
    }
}