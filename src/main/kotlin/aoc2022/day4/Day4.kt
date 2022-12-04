package main.aoc2022.day4
import java.io.File

fun part1(input: File) : Int =input.readLines().sumOf(::getOverLapsP1)
fun part2(input: File) : Int = input.readLines().sumOf(::getOverLapsP2)

private fun getOverLapsP1(it: String) : Int {
    val (a,b) = it.split(",").map{it.split("-").map(String::toInt) }
    return if (a[0] <= b[0] && a[1] >= b[1] || b[0] <= a[0] && b[1] >= a[1]) 1 else 0
}

private fun getOverLapsP2(it: String) : Int {
    val (a,b) = it.split(",").map{it.split("-").map(String::toInt) }.map { it[0]..it[1] }
    return if (a.intersect(b).isNotEmpty()) 1 else 0
}
