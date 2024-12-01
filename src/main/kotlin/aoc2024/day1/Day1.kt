package main.aoc2024.day1

import java.io.File
import kotlin.math.absoluteValue

fun part1(input: File) : Long = distanceBetweenLists(input.readLines())

fun part2(input: File) : Long = similarity(input.readLines())

fun distanceBetweenLists(readLines: List<String>): Long {

    val regex = "\\D+".toRegex()

    val first =  readLines.map { s -> s.split(regex).map{it.toLong()}.take(1)}.flatten().sorted()
    val last =  readLines.map { s -> s.split(regex).map{it.toLong()}.takeLast(1)}.flatten().sorted()

   return first.zip(last).sumOf { (f, l) -> (f - l).absoluteValue }
}

fun similarity(readLines: List<String>): Long {
    val regex = "\\D+".toRegex()
    val first =  readLines.map { s -> s.split(regex).map{it.toLong()}.take(1)}.flatten()
    val last =  readLines.map { s -> s.split(regex).map{it.toLong()}.takeLast(1)}.flatten().groupBy { it }
    return first.sumOf { it * (last[it]?.size ?: 0) }
}