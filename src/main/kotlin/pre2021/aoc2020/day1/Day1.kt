package main.pre2021.aoc2020.day1

import java.io.File

fun day1() = fuel(File("src/pre2021.adventOfCode.pre2021.day1.pre2021.day1/pre2021.adventOfCode.pre2021.day1.pre2021.day1.txt").readLines().map { it.toInt() })

  private fun calc(x : Int) : Int {
        val y = x/3 - 2
        return if(y > 8)
            y + calc(y)
        else
            y
    }

  private fun fuel(list: List<Int>) : Int {
        if(list.count() > 0)
            return calc(list.first()) + fuel(list.drop(1))
        return 0
    }
