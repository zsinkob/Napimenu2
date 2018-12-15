package hu.zsinko.dailymenu.provider

import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class Talka(private val facebookService: FacebookService) : MenuProvider {

    private val restaurant = Restaurant("Tálka", "https://www.facebook.com/talkakavezo")

    override fun getMenu(date: LocalDate): Menu {
        val post = facebookService.getPost("talkakavezo") { it.picture != null }

        return if (post != null && post.objectId != null) {
            val photo = facebookService.getPhoto(post.objectId)
            Menu("", LocalDate.now(), restaurant, photo)
        } else {
            generateMenu("", restaurant)
        }
    }
}