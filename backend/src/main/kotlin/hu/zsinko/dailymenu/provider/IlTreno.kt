package hu.zsinko.dailymenu.provider

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDate

@Service
class IlTreno : MenuProvider {
    private val restaurant = Restaurant("Il Treno", "https://www.iltreno.hu/vacigyorsetterem/")

    override fun getMenu(date: LocalDate): Menu {
        val currentDay = DayOfWeek.from(LocalDate.now())
        val doc = Jsoup.connect("http://www.iltreno.hu/vacigyorsetterem/").get()
        val menu = doc.run {
            select("tbody tr td").map(Element::ownText).filter { !it.trim().isEmpty() }
        }
        val days = doc.run {
            select("thead tr th").map(Element::ownText).filter { !it.trim().isEmpty() }
        }
        return if(menu.size >= currentDay.value &&
                days.size >= currentDay.value &&
                days[currentDay.value - 1] == hunDays[currentDay]) {
            generateMenu(menu[currentDay.value - 1], restaurant)
        } else {
            generateMenu("", restaurant)
        }
    }
}