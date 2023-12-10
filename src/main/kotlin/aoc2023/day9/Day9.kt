package main.aoc2023.day9

import java.io.File

fun part1(input: File) : Long = findHistorySum(input.readLines())
fun part2(input: File) : Long = findHistorySum2(input.readLines())

fun findHistorySum(readLines: List<String>): Long {

    //return readLines.sumOf { s->  generateSequence(s.split(" ").map{it.toInt()}) { c -> c.windowed(2){(a,b)-> b-a } }.takeWhile { it.any{d -> d != 0} }.sumOf { it.last() }}.toLong()

    return readLines.sumOf { s->
        val list = s.split(" ").map { d -> d.toInt() }
        extrapolate(list).toLong() + list[list.lastIndex]
    }
}

fun extrapolate(nums: List<Int>): Int {
    val l = mutableListOf<Int>()
     nums.forEachIndexed { index, i ->
        if(index < nums.size-1)
        l.add(nums[index+1]-i)
    }
    if(l.all { it == l[0] })
        return l[l.lastIndex]
    return l[l.lastIndex] + extrapolate(l)
}

fun extrapolateBackwards(nums: List<Int>): Int {

    val l = mutableListOf<Int>()
    nums.forEachIndexed { index, i ->
        if(index < nums.size-1)
            l.add(nums[index+1]-i)
    }
    if(l.all { it == l[0] })
        return l[0]

    return l[0] - extrapolateBackwards(l)
}

fun findHistorySum2(readLines: List<String>): Long {
    return readLines.sumOf { s->
        val list = s.split(" ").map { d -> d.toInt() }
        list[0] - extrapolateBackwards(list).toLong()
    }
}



