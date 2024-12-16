package main.aoc2024.day5

import java.io.File
import java.util.Collections
import java.util.HashSet

fun part1(input: File) : Int = findSafeInstructions(input.readLines())
fun part2(input: File) : Int = findSafeInstructions2(input.readLines())

fun findSafeInstructions(readLines: List<String>): Int {
    val rules = readLines.takeWhile { it.isNotBlank() }.toHashSet()
    val updates = readLines.takeLastWhile { it.isNotBlank() }.map {  it.split(",").map { it.toInt() } }

    return updates.sumOf { list ->
            val mutableList = getListOfPairs(list)
        if( mutableList.any{ l ->
             rules.contains(l)})
            "0".toInt()
         else {
             list[(list.size/2)]
        }
    }
}

fun findSafeInstructions2(readLines: List<String>): Int {
    val rules = readLines.takeWhile { it.isNotBlank() }.toHashSet()
    val updates = readLines.takeLastWhile { it.isNotBlank() }.map {  it.split(",").map { it.toInt() } }

    return updates.sumOf { list ->
        val mutableList = getListOfPairs(list)
        if(mutableList.any{l ->
                    rules.contains(l)})
            fixInputAndReturnMiddleNumber(rules,list, mutableList)
        else {
            0
        }
    }
}

private fun getListOfPairs(list: List<Int>): MutableList<String> {
    val mutableList = mutableListOf<String>()
    list.forEachIndexed { index, i ->
        list.takeLast(list.size - index).filter { y -> y != i }.forEach {
            mutableList.add("$it|$i")
        }
    }
    return mutableList
}

fun fixInputAndReturnMiddleNumber(rules: HashSet<String>, list: List<Int>, mutableList: MutableList<String>): Int {
    val ml =list.toMutableList()
    var whileList = mutableList;
    while(whileList.any(){ m -> rules.contains(m)}){
                val trouble = whileList.first() { m -> rules.contains(m) }.split("|").map { it.toInt() }
                Collections.swap(ml,ml.indexOf(trouble[0]),ml.indexOf(trouble[1]))
       whileList = getListOfPairs(ml)
            }
      return  ml[(ml.size/2)]
}


