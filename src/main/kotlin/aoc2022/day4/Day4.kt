package main.aoc2022.day4
import java.io.File

fun part1(input: File) : Int =input.readLines().sumOf(::getOverLapsP1)
fun part2(input: File) : Int = input.readLines().sumOf(::getOverLapsP2)

private fun getOverLapsP1(it: String) : Int {
    val ranges = getRangesAsSets(it)
    val commonSize = ranges[0].intersect(ranges[1]).size
    return if (commonSize == ranges[0].size || commonSize == ranges[1].size) 1 else 0
}

private fun getOverLapsP2(it: String) : Int {
    val ranges = getRangesAsSets(it)
    return if (ranges[0].intersect(ranges[1]).isNotEmpty()) 1 else 0
}

private fun getRangesAsSets(it: String) = it.split(",").map { s ->
    val intList = s.split("-").map { it.toInt() }
    (intList[0]..intList[1]).toSet()
}
