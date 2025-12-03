package main.aoc2025.day3

import java.io.File

fun part1(input: File) : Long = findJoltage(input.readLines())

fun findJoltage(readLines: List<String>): Long {

   val sum = readLines.sumOf { findTwoHighestNumbersAsInt(it) }

    return sum.toLong()
}

fun findTwoHighestNumbersAsInt(numbers: String) : Int {
   val largest2 = numbers.fold("00"){acc, ch ->
        val num = ch.code
        val first = acc[0].code
        val second = acc[1].code
        if(num > second && second >= first)
            acc[1]+ch.toString()
        else if (num > second)
                acc[0]+ch.toString()
        else if(second > first)
            acc[1]+ch.toString()
        else
        acc
    }

    return largest2.toInt()
}

fun part2(input: File) : Long = findJoltage2(input.readLines())

fun findJoltage2(readLines: List<String>): Long {

    val sum = readLines.sumOf { findTwelveHighestNumbersAsInt2(it) }

    return sum

}


fun findTwelveHighestNumbersAsInt(numbers: String) : Long {

    var mutableString = numbers
    val smt = (11 downTo 0).fold("") { acc, i ->
        val start =mutableString.take(mutableString.length - i).mapIndexed { index, ch ->
            Pair(index, ch)
        }.maxBy { it.second.code }
        mutableString = mutableString.substring(start.first + 1)
        acc + start.second
    }

    return smt.toLong()




}
fun findTwelveHighestNumbersAsInt2(numbers: String) : Long {

    val smt = (11 downTo 0).fold(Pair(numbers,"")) { acc, i ->
        val start = acc.first.take(acc.first.length - i).mapIndexed { index, ch ->
            Pair(index, ch)
        }.maxBy { it.second.code }
        Pair(acc.first.substring(start.first+1),acc.second + start.second)
    }
    return smt.second.toLong()

}

