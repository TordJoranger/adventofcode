package main.aoc2023.day1

import java.io.File

fun part1(input: File) : Int = countFirstAndLast(input.readLines())
fun part2(input: File) : Int = countFirstAndLast2(input.readLines())

fun countFirstAndLast(readLines: List<String>): Int {
    return readLines.sumOf { l -> l.first { s -> s.digitToIntOrNull() != null }
        .plus(l.last { s -> s.digitToIntOrNull() != null }.toString()).toInt() }
}

fun countFirstAndLast2(readLines: List<String>): Int {

    return readLines.sumOf { l-> (findFirstDigit(l)+findLastDigit(l)).toInt() }
}

fun findFirstDigit(l: String): String {
    val col = listOf("one","two","three","four","five","six","seven","eight","nine","1","2","3","4","5","6","7","8","9")
    return mapToDigitString(l.findAnyOf(col)!!.second)
}

fun findLastDigit(l: String): String {
    val col = listOf("one","two","three","four","five","six","seven","eight","nine","1","2","3","4","5","6","7","8","9")
    return mapToDigitString(l.findLastAnyOf(col)!!.second)
}

private fun mapToDigitString(s: String): String {
    return when(s){
        "one" -> "1"
        "two" -> "2"
        "three" -> "3"
        "four" -> "4"
        "five" -> "5"
        "six" -> "6"
        "seven" -> "7"
        "eight" -> "8"
        "nine" -> "9"
        else -> s
    }
}


