package main.aoc2024.day2
import java.io.File

fun part1(input: File) : Int = safeLevels(input.readLines())

fun safeLevels(readLines: List<String>): Int {
  return readLines.map { s -> s.split(" ").map { it.toInt() } }.sumOf {
      if(isDescending(it) + isAscending(it) == 1)
          "1".toInt()
      else
          0
  }
}

fun anySafeLevels(readLines: List<String>): Int {
    return readLines.map { s -> s.split(" ").map { it.toInt() } }.sumOf {
        if(isAnyDescending(it) + isAnyAscending(it) == 1)
            "1".toInt()
        else
            0
    }
}

fun isAnyAscending(levels: List<Int>): Int {
    return if(List(levels.size) { oi ->
            if(isAscending(levels.filterIndexed { index, _ -> index != oi }) == 1)
                1
            else
                0
        }.any{ i -> i ==1})
        1
    else
        0
}

fun isAnyDescending(levels: List<Int>): Int {
    return if(List(levels.size) { oi ->
            if(isDescending(levels.filterIndexed { index, _ -> index != oi }) == 1)
                1
            else
                0
        }.any{ i -> i ==1})
        1
    else
        0
}

fun isAscending(levels: List<Int>): Int {
    return levels.zipWithNext().map { i ->
        if(i.second - i.first in 1..3)
            1
        else
            0
    }.reduce { acc, i -> acc * i }
}

fun isDescending(levels: List<Int>): Int {
    return  levels.zipWithNext().map { i ->
        if(i.first - i.second in 1..3)
           1
        else
          0
    }.reduce { acc, i -> acc * i }
}

fun part2(input: File) : Int = anySafeLevels(input.readLines())