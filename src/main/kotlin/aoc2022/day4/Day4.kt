package main.aoc2022.day4
import java.io.File

fun part1(input: File) : Int = getOverLapsP1(input.readLines())
fun part2(input: File) : Int = getOverLapsP2(input.readLines())

private fun getOverLapsP1(list: List<String>) : Int = list.count{ l ->
     val (a, b) = l.split(",").map { it.split("-").map(String::toInt) }
     (a[0] <= b[0] && a[1] >= b[1] || b[0] <= a[0] && b[1] >= a[1])
}

private fun getOverLapsP2(list: List<String>) : Int = list.count { l ->
     val (a, b) = l.split(",").map { it.split("-").map(String::toInt) }.map { it[0]..it[1] }
     (a.intersect(b).isNotEmpty())
 }
