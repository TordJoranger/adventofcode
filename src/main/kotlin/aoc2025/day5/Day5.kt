package main.aoc2025.day5

import io.ktor.util.length
import java.io.File

fun part1(input: File) : Int = freshFood(input.readLines())

fun freshFood(readLines: List<String>): Int {
    val  ranges = readLines.takeWhile { it != "" }.map { s->
        val split = s.split("-")
        split[0].toLong() .. split[1].toLong()
    }
    val inputs = readLines.takeLastWhile { it != "" }
    val result = inputs.count { n -> ranges.any{ r -> r.contains(n.toLong())} }
    return result
}

fun part2(input: File) : Long = freshFood2(input.readLines())

fun freshFood2(readLines: List<String>): Long {


    val sortedRanges = readLines.takeWhile { it != "" }.map { s ->
        val split = s.split("-")
        split[0].toLong()..split[1].toLong()
    }.sortedBy { it.first }
    val  clearRanges = sortedRanges.foldIndexed(mutableListOf<LongRange>()) { index, acc, longs ->
        if(index == 0) {
            acc.add(longs)
            acc
        }
       else if(longs.first > acc.last().last()) {  //clear range
            acc.add(longs)
            acc
        }
        else if(longs.first <= acc.last().last){ //range overlaps
            if(acc.last().last < longs.last) {
                val startOfNewRange = acc.last().first
                acc.removeLast()
                acc.add(startOfNewRange.. longs.last)
            }
            acc
        }
        else
       acc
    }

     val sum = clearRanges.sumOf { (it.last -it.first)+1 }

    return sum

}
