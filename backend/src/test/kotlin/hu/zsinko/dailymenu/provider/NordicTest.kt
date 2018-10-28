package hu.zsinko.dailymenu.provider

import org.junit.Test
import org.springframework.social.facebook.api.impl.FacebookTemplate
import java.time.LocalDate

class NordicTest {
    @Test
    fun testPictureResponse() {
        val facebook = FacebookService(FacebookTemplate("582813855245984|_Rssln5VgoP05inf_FgincK4iy4"))
        val nordic = Nordic(facebook)
        val menu = nordic.getMenu(LocalDate.now())
        println(menu)
    }
}