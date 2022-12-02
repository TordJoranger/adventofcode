package main.aoc2021.day1

import java.io.File

fun day1() = countIncrementsPart2(File("src/aoc2021/pre2021.day1/pre2021.day1.txt").readLines())

fun countIncrements(list: List<String>): Int {

    var count = 0

    list.forEachIndexed { index, s ->
        if (index != 0 && s.toInt() > list[index - 1].toInt())
            count += 1
    }


    return count

}

fun countIncrementsPart2(list: List<String>): Int {

    var count = 0
    val ints = list.map { s-> s.toInt() };
    val size = list.size

   ints.forEachIndexed { index, s ->
       if(index != 0 && index < size -2) {
           val previous = ints[index+1] + s + ints[index-1]
           val current = s+  ints[index+1] + ints[index+2]

           if (current > previous)
               count += 1
       }


    }


    return count

}

