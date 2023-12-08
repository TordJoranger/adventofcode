package main.aoc2023.day8

import java.io.File
import kotlin.text.toCharArray

fun part1(input: File) : Long = findZZZ(input.readLines())
fun part2(input: File) : Long = findZZZ2(input.readLines())

private fun findZZZ(readLines: List<String>): Long {

    val instructions = readLines[0].toCharArray()
    val map =  readLines.drop(2).groupBy { it.substring(0,3) }.mapValues { it.value[0].substring(7,10) to it.value[0].substring(12,15) }
    val count = findZZs(instructions, map)
    return count.toLong()
}

private fun findZZs(
    instructions: CharArray,
    map: Map<String, Pair<String, String>>
): Int {
    var currentNode = "AAA"
    var count = 0
    while (currentNode != "ZZZ") {

        val dir = instructions[count % instructions.size]
        currentNode = if (dir == 'L')
            map[currentNode]!!.first
        else
            map[currentNode]!!.second

        count++
    }
    return count
}

private fun findEndsWithZ(
    currentNode: String,
    instructions: CharArray,
    map: Map<String, Pair<String, String>>
): Int {
    var currentNode1 = currentNode
    var count = 0
    while (!currentNode1.endsWith("Z")) {

        val dir = instructions[count % instructions.size]
        currentNode1 = if (dir == 'L')
            map[currentNode1]!!.first
        else
            map[currentNode1]!!.second

        count++
    }
    return count
}

private fun findZZZ2(readLines: List<String>): Long {
    val instructions = readLines[0].toCharArray()
    val map =  readLines.drop(2).groupBy { it.substring(0,3) }.mapValues { it.value[0].substring(7,10) to it.value[0].substring(12,15) }
    val currentNodes = map.filter { it.key.endsWith("A") }.map { it.key }.toMutableSet()
    val lowestZs =  currentNodes.map { findEndsWithZ(it,instructions,map).toLong() }
    return findLCMOfListOfNumbers(lowestZs)
}

fun findLCMOfListOfNumbers(numbers: List<Long>): Long {
    var result = numbers[0]
    for (i in 1 until numbers.size) {
        result = findLCM(result, numbers[i])
    }
    return result
}

fun findLCM(a: Long, b: Long): Long {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}



