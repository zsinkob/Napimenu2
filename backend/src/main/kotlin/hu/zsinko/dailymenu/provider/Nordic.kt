package hu.zsinko.dailymenu.provider

import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class Nordic(private val facebookService: FacebookService) : MenuProvider {

    private val restaurant = Restaurant("Nordic Light", "https://www.facebook.com/Nordic-Light-%C3%89tterem-%C3%A9s-k%C3%A1v%C3%A9z%C3%B3-804040813067079/")

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