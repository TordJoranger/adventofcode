package main.aoc2025.day2

import java.io.File

fun part1(input: File) : Long = findInvalidIds(input.readLines())

fun findInvalidIds(readLines: List<String>): Long {
    val regex = "\\D+".toRegex()
   val inputs =  readLines[0].split(",").map { ranges -> ranges.split(regex) }.map { it[0].toLong()..it[1].toLong() }

    val result = inputs.sumOf { countInvalidIds(it) }
    return result
}

fun countInvalidIds(range: LongRange) : Long {
    return range.sumOf {  lng ->
        val srt = lng.toString()
        if(srt.length %2 == 0 && srt.take(srt.length/2) == srt.takeLast(srt.length/2))
             lng

        else 0

     }
}

fun part2(input: File) : Long = findInvalidIds2(input.readLines())

fun findInvalidIds2(readLines: List<String>): Long {
    val regex = "\\D+".toRegex()
    val inputs =  readLines[0].split(",").map { ranges -> ranges.split(regex) }.map { it[0].toLong()..it[1].toLong() }

    val result = inputs.sumOf { countInvalidIds2(it) }
    return result
}

fun countInvalidIds2(range: LongRange) : Long {
    return range.sumOf { lng ->
        val srt = lng.toString()
        val invalid = (srt.length/2 downTo 1).any { win ->
            val chunked = srt.chunkedSequence(win)
            chunked.all{it == chunked.first() }
        }
        if (invalid)
            lng
        else 0

    }
}