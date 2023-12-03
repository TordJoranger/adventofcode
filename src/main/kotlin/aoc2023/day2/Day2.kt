package main.aoc2023.day2

import java.io.File

fun part1(input: File) : Int = countCubes1(input.readLines())
fun part2(input: File) : Int = countCubes2(input.readLines())


fun countCubes1(readLines: List<String>): Int {
    return readLines.mapIndexed{ index, s ->

        if(!anySumOverMax(s,"red", 12)  && !anySumOverMax(s,"blue", 14)  && !anySumOverMax(s,"green",13))
            index+1
        else
            0
    }.sum()
}

fun countCubes2(readLines: List<String>): Int {
   return readLines.sumOf {  s ->
        maxSum(s, "red") * maxSum(s, "green") * maxSum(s, "blue")
   }
}

fun maxSum(s: String, color : String): Int {

    val regex = color.toRegex()
    return regex.findAll(s).map { it.range.first - 2 }.maxOf { i -> s.subSequence(i-1,i+1).trim().toString().toInt() }

}

fun anySumOverMax(s: String, color : String, max : Int): Boolean {

    val regex = color.toRegex()
    return regex.findAll(s).map { it.range.first - 2 }.any { i -> s.subSequence(i-1,i+1).trim().toString().toInt() > max }

}
