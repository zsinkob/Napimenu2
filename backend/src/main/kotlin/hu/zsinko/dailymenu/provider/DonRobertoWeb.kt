package hu.zsinko.dailymenu.provider

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDate

@Service
class DonRobertoWeb : MenuProvider {
    private val restaurant = Restaurant("Don Roberto", "https://www.donroberto.hu/etlap/napi_menu")

    override fun getMenu(date: LocalDate): Menu {
        val currentDay = DayOfWeek.from(LocalDate.now())
        val doc = Jsoup.connect("https://www.donroberto.hu/etlap/napi_menu").get()
        if (doc.select("div:contains(Üres kategória)").isNotEmpty()) {
            return generateMenu("", restaurant)
        }
        val menu = doc.run {
            select("div.product-meta").map(Element::text).filter { !it.trim().isEmpty() }.joinToString("\n")
        }
        return generateMenu(menu, restaurant)

    }
}