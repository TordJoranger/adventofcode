package main.aoc2023.day6

import java.io.File

fun part1(input: File) : Long = findLowestWinning(input.readLines())

fun findLowestWinning(readLines: List<String>): Long {
    val regex = "\\d+".toRegex()
    val time = regex.findAll(readLines[0]).map { m -> m.value.toLong() }.toList()
    val distance = regex.findAll(readLines[1]).map { m -> m.value.toLong() }.toList()

   return time.mapIndexed { index, t ->
       val x =(1 until t).sumOf { c->
           if(c*(t-c) > distance[index])
               1L
           else
               0
       }
   x
   }.reduce { acc, l -> acc*l }
}

fun part2(input: File) : Long = findLowestWinning2(input.readLines())

fun findLowestWinning2(readLines: List<String>): Long {
    val regex = "\\d+".toRegex()
    val time = regex.findAll(readLines[0]).map { m -> m.value }.reduce{a,b-> a+b}.toLong()
    val distance = regex.findAll(readLines[1]).map { m -> m.value }.reduce{a,b-> a+b}.toLong()

    return (1 until time).sumOf { c->
            if(c*(time-c) > distance)
                1L
            else
                0
        }
}
