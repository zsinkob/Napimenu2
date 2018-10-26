package hu.zsinko.dailymenu.provider

import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter



@Service
class DonRoberto(private val facebookService: FacebookService): MenuProvider {

    private val restaurant = Restaurant("Don Roberto", "Robert Károly körút")
    private val dateFormat = DateTimeFormatter.ofPattern("MM.dd.")

    override fun getMenu(date: LocalDate): Menu {
        val post = facebookService.getPost("DonRobertoPizzeria") { Regex(dateFormat.format(date)).containsMatchIn(it) }
        return generateMenu(post.linesBetween("CLASSIC","menü"), restaurant)
    }
}