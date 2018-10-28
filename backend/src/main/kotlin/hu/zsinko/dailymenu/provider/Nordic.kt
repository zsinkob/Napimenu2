package hu.zsinko.dailymenu.provider

import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class Nordic(private val facebookService: FacebookService) : MenuProvider {

    private val restaurant = Restaurant("Nordic Light", "Irodával szemben")

    override fun getMenu(date: LocalDate): Menu {
        val post = facebookService.getPost("804040813067079") { it.picture != null }

        return if (post != null && post.objectId != null) {
            val photo = facebookService.getPhoto(post.objectId)
            Menu("", LocalDate.now(), restaurant, photo)
        } else {
            generateMenu("", restaurant)
        }
    }
}