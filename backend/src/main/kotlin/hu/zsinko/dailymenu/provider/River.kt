package hu.zsinko.dailymenu.provider

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class River : MenuProvider {
    private val restaurant = Restaurant("River", "http://rivercatering.hu/ETLAP/menuriver.html")
    override fun getMenu(date: LocalDate): Menu {

        val menu = Jsoup.connect("http://rivercatering.hu/ETLAP/menuriver.html").get().run {
            select("tbody tr").map(Element::text).elementsBetween("LEVESEK", "KÖRETEK", true, true).joinToString("\n")
        }
        return generateMenu(menu, restaurant)
    }
}