package main.aoc2022.day3
import java.io.File

fun run(input: File) : String = day3(input.readLines())
val charArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()

private fun day3(list: List<String>): String =
    "part1: ${part1(list)}" +
    "\r\n\r\n" +
    "part2: ${ part2(list) }"

private fun part1(list: List<String>) = list.sumOf { s -> calculateSumP1(s) }

fun calculateSumP1(s: String) : Int {
    val compartment1 = s.toCharArray().take(s.length/2)
    val compartment2 = s.toCharArray().takeLast(s.length/2)
    return getScore(compartment1.intersect(compartment2.toSet()).first())
}

private fun part2(list: List<String>): Int {
    return list.chunked(3).map { s -> s.map { it.toCharArray().toSet() } }.sumOf { calculateSumP2(it) }
}
fun calculateSumP2(s: List<Set<Char>>) : Int {
    return getScore(s[0].intersect(s[1]).intersect(s[2]).first())
}

private fun getScore(item: Char): Int {
    return charArray.indexOf(item) +1
}



