package hu.zsinko.dailymenu.provider

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.junit.Test
import org.springframework.social.facebook.api.impl.FacebookTemplate
import java.time.LocalDate
import java.util.*

class NordicTest {
    @Test
    fun testPictureResponse() {
        val facebook = FacebookService(FacebookTemplate(loadToken()))
        val nordic = Nordic(facebook)
        val menu = nordic.getMenu(LocalDate.now())
        println(menu)
    }

    private fun loadToken(): String? {
        val prop = Properties()
        val propertiesStream = NordicTest::class.java!!.classLoader.getResourceAsStream("application.properties")
        prop.load(propertiesStream)
        return prop.getProperty("menu.facebooktoken")
    }


    @Test
    fun testIlTreno() {
        IlTreno().getMenu(LocalDate.now())

        println(IlTreno().getMenu(LocalDate.now()))

    }
}