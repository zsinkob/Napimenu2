package hu.zsinko.dailymenu.provider

import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class Atrium(private val facebookService: FacebookService) : MenuProvider {

    private val restaurant = Restaurant("Átrium Eurest", "http://www.eurest.hu/ettermeink/atrium-park/")

    override fun getMenu(date: LocalDate): Menu {
        return generateMenu("", restaurant)
    }
}
