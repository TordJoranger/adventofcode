package main.aoc2023.day11

import java.io.File
import kotlin.math.absoluteValue

fun part1(input: File) : Long = findDistanceSum(input.readLines())
fun part2(input: File) : Long = findDistanceSum2(input.readLines())

fun findDistanceSum2(readLines: List<String>): Long {
    val columnIdexes = mutableListOf<Int>()
    (0 until readLines.first().toCharArray().size).forEach { r ->
        if(readLines.all { s -> s[r] == '.' }) {
            columnIdexes.add(r)
        }
    }
    val rowIdexes = readLines.foldIndexed(listOf()) { index: Int, acc : List<Int>, s: String ->
        if(s.all { c -> c == '.' }){
            acc.plus(index)
        }else
            acc
    }
    val queue = mutableListOf<Pair<Int,Int>>()
    readLines.forEachIndexed { i1, s ->
        s.forEachIndexed { i2, c ->
            if(c == '#')
                queue.add(i1 to i2)
        }
    }
    var sum = 0L
    queue.forEachIndexed { index, pair ->
        sum += queue.drop(index+1).sumOf {
            calculateDistance(pair,it,rowIdexes,columnIdexes,999999L)
        }
    }

    return  sum
}

fun findDistanceSum(readLines: List<String>): Long {
    val columnIdexes = mutableListOf<Int>()
    (0 until readLines.first().toCharArray().size).forEach { r ->
        if(readLines.all { s -> s[r] == '.' }) {
            columnIdexes.add(r)
        }
    }
    val rowIdexes = readLines.foldIndexed(listOf()) { index: Int, acc : List<Int>, s: String ->
        if(s.all { c -> c == '.' }){
            acc.plus(index)
        }else
            acc
    }
    val queue = mutableListOf<Pair<Int,Int>>()
    readLines.forEachIndexed { i1, s ->
        s.forEachIndexed { i2, c ->
            if(c == '#')
                queue.add(i1 to i2)
        }
    }
    var sum = 0L
   queue.forEachIndexed { index, pair ->
       sum += queue.drop(index+1).sumOf {
           calculateDistance(pair, it, rowIdexes, columnIdexes, 1L)
       }
   }

    return  sum

}

fun calculateDistance(
    pair: Pair<Int, Int>,
    pair2: Pair<Int, Int>,
    rowIndexes: List<Int>,
    columnIndexes: MutableList<Int>,
    i: Long
): Long {

    val distance = (pair2.first - pair.first).absoluteValue + (pair2.second - pair.second).absoluteValue
   val columns = columnIndexes.sumOf {
        if(it in pair2.second until pair.second || it in pair.second..pair2.second )
            i
        else
            0L
    }

    val rows = (pair.first   until pair2.first).sumOf {
       if(rowIndexes.contains(it))
            i
        else
            0L
    }

    return distance+rows+columns
}


