package hu.zsinko.dailymenu.provider

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