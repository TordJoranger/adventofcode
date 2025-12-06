package main.aoc2025.day6

import main.common.functions.split
import main.common.functions.vertical
import java.io.File

fun part1(input: File) : Long = sumColumns(input.readLines())

fun sumColumns(readLines: List<String>): Long {
    val digitRegex = "\\d+".toRegex()

    val map = readLines.map { it ->
        digitRegex.findAll(it).map { it.value.toInt() }.toList()
    }.dropLast(1)

    val vertical = map[0].indices.map { x -> map.indices.map { y -> map[y][x] }.joinToString(" ") }

    val ops = readLines.last().filter { it != ' ' }.toCharArray()
    val results = vertical.mapIndexed { index, a->
        val ints =  a.split(" ").map { it.toLong() }
        val operator = ops[index]
        val reduced = ints.reduce { acc, i ->
            when (operator){
                '+' ->  acc+i
                '*' -> acc*i
                else -> 0
            }
        }
        reduced
    }
    return results.sum()
}
fun part2(input: File) : Long = sumColumns2(input.readLines())

fun sumColumns2(readLines: List<String>): Long {
   val vert = vertical(readLines.dropLast(1))

    val ops = readLines.last().filter { it != ' ' }.toCharArray()
    val filtered = vert.split {  it ==  "       " }.map { it.map { it.filter { it != ' ' }.toLong() } }
    val results = filtered.mapIndexed { index, a->
        val operator = ops[index]
        val reduced = a.reduce { acc, i ->
            when (operator){
                '+' ->  acc+i
                '*' -> acc*i
                else -> 0
            }
        }
        reduced
    }
    return results.sum()
}

