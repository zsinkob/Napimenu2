package hu.zsinko.dailymenu.provider

import java.time.DayOfWeek

fun String.linesBetween(start: String, end: String): String =
        linesBetween(start, end, true, false)

fun String.linesBetween(start: String, end: String, includeStart: Boolean = true, fullIfEmpty: Boolean = false): String =
    Regex("\n").split(this).elementsBetween(start, end, includeStart, fullIfEmpty).joinToString("\n")

fun List<String>.elementsBetween(start: String, end: String, includeStart: Boolean = true, fullIfEmpty: Boolean = false): List<String> {
    var extract = false
    val result = arrayListOf<String>()
    this.forEach {
        if (Regex(start).containsMatchIn(it)) {
            extract = true
            if(!includeStart) return@forEach
        }
        if (Regex(end).containsMatchIn(it)) {
            extract = false
        }
        if(extract) {
            result.add(it)
        }
    }

    return if(result.isEmpty() && fullIfEmpty) {
        this
    } else {
        result
    }

}

val hunDays = hashMapOf(DayOfWeek.MONDAY to "Hétfő",
        DayOfWeek.TUESDAY to "Kedd",
        DayOfWeek.WEDNESDAY to "Szerda",
        DayOfWeek.THURSDAY to "Csütörtök",
        DayOfWeek.FRIDAY to "Péntek",
        DayOfWeek.SATURDAY to "Szombat",
        DayOfWeek.SUNDAY to "Vasárnap"
)