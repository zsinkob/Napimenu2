package hu.zsinko.dailymenu.provider

import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class ZsuAnn(private val facebookService: FacebookService) : MenuProvider {

    private val restaurant = Restaurant("Zsu Ann", "Messze")

    override fun getMenu(date: LocalDate): Menu {
        val post = facebookService.getPost("368151033195185") { it.picture != null }

        return if (post != null && post.objectId != null) {
            val photo = facebookService.getPhoto(post.objectId)
            Menu("", LocalDate.now(), restaurant, photo)
        } else {
            generateMenu("", restaurant)
        }
    }
}