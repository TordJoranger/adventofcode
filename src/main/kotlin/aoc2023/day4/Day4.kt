package main.aoc2023.day4

import java.io.File
import kotlin.math.pow

fun part1(input: File) : Long = getWinningNumbers(input.readLines())

private fun getWinningNumbers(readLines: List<String>): Long {
    val regex = "\\d+".toRegex()
    return readLines.sumOf { s ->

            val list = s.takeLastWhile { c -> c != ':' }.split("|").map { l -> regex.findAll(l).toSet().map { m -> m.value.toInt() } }
            val intersect = list[0].intersect(list[1].toSet())

            val sum = 2.0.pow(intersect.size-1).toLong()

        sum
        }
    }

    fun part2(input: File) : Int = getWinningNumbers2(input.readLines())

    private fun getWinningNumbers2(readLines: List<String>): Int {
        val sum = mutableListOf<String>()
            readLines.forEachIndexed { index, _ ->
                runSim(readLines.drop(index), sum)
            }
        return sum.size
    }

private fun runSim(
    readLines: List<String>,
     sum: MutableList<String>
)  {
    sum.add(readLines[0].takeWhile { c -> c!= ':' })
    val regex = "\\d+".toRegex()
    val list = readLines[0].takeLastWhile { c -> c != ':' }.split("|")
        .map { l -> regex.findAll(l).toList().map { m -> m.value.toInt() } }
    val intersect = list[0].intersect(list[1].toSet())

    if (intersect.isNotEmpty()) {
        for (i in 1..intersect.size) {
             runSim(readLines.drop(i), sum)
        }
    }
}


