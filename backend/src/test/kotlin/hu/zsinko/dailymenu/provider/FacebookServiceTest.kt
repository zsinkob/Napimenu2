package hu.zsinko.dailymenu.provider

import org.junit.Test

class FacebookServiceTest {
    //@Test
    fun testRegex() {
        val post = "NAPI AJÁNLAT (10.19.)\n" +
                "CLASSIC MENÜ\n" +
                "Forró fahéjas almaleves és Fokhagymás sült csirkeszárny snidlinges törtburgonya\n" +
                "\n" +
                "BUSINESS MENÜ\n" +
                "Forró fahéjas almaleves és Bakonyi sertésszelet galuskával\n" +
                "\n" +
                "menü #ebéd #napimenü #étterem #pizzéria #pizza"

        val value = Regex("""CLASSIC(.*)ebéd""", setOf(RegexOption.DOT_MATCHES_ALL)).find(post) ?: return
        val lines = Regex("\n").split(value.value)

        val extracted = arrayListOf<String>()
        for(line in lines) {
            if(!line.trim().isEmpty() && !Regex("menü").containsMatchIn(line)) {
                extracted.add(line)
            }
        }
        println(extracted.joinToString("\n"))

    }
}

