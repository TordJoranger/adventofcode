package main.aoc2022.day6


import java.io.File

fun part1(input: File) : Int = doSmt(input.readText(),4)
fun part2(input: File) : Int = doSmt(input.readText(),14)

fun doSmt(readLines: String, num : Int): Int {
    readLines.toCharArray().foldIndexed("") { index, acc, c ->
        if((acc+c).toCharArray().distinct().size == num)
          return  index +1
        if(acc.length > num-2)
          acc.substring(1) +c
        else
          acc + c
    }
    return 0
}
