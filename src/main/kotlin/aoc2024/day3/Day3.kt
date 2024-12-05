package main.aoc2024.day3

import java.io.File

fun part1(input: File) : Int = multiply(input.readLines())

fun multiply(readLines: List<String>): Int {
    val regex = "mul\\(\\d*,\\d*\\)".toRegex()
    val digitRegex = "\\d+".toRegex()
   val sum = readLines.flatMap { s -> regex.findAll(s) }.map {
       r-> digitRegex.findAll(r.value)
           .map { m -> m.value.toInt() }
   }.sumOf { it.reduce{a,b -> a*b}  }


    return sum
}

fun part2(input: File) : Int = multiplyIfDo(input.readLines())

fun multiplyIfDo(readLines: List<String>): Int {
    val regex = "mul\\(\\d*,\\d*\\)".toRegex()
    val digitRegex = "\\d+".toRegex()
    val doRegex = "do()".toRegex()
    val doNotRegex = "don't()".toRegex()

    val s = readLines.reduce { acc, s -> acc+s }
        val muls  = regex.findAll(s)
        val dos = doRegex.findAll(s).map { m -> m.range.first }
        val doNots = doNotRegex.findAll(s).map { m -> m.range.first }

        val filteredMuls = muls.filter { m-> !doNots.any{ dn -> dn < m.range.first && !dos.any{ d -> d > dn && d < m.range.first } } }


     return   filteredMuls.map {  r-> digitRegex.findAll(r.value)
                .map { m -> m.value.toInt() } }
    .sumOf { it.reduce{a,b -> a*b}  }

}
