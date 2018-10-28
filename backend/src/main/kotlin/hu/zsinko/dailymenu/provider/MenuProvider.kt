package hu.zsinko.dailymenu.provider

import java.time.LocalDate

interface MenuProvider {
    fun getMenu(date: LocalDate): Menu
}

fun generateMenu(menu: String, restaurant: Restaurant): Menu = Menu(menu, LocalDate.now(), restaurant)

data class Restaurant(
        val name: String,
        val address: String
)

data class Menu(
        val menu: String,
        val date: LocalDate,
        val restaurant: Restaurant,
        val image: String? = null
)