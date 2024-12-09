package main.aoc2024.day7
import java.io.File

fun part1(input: File) : Long = findSafeEquations(input.readLines())

fun findSafeEquations(readLines: List<String>): Long {
    return readLines.sumOf { l ->
        val value = l.split(":").first().toLong()
        val n = l.split(":").last().trim()
        val nums = n.split(" ").map { it.toLong() }
        if(canBeSolved(value,nums.drop(1),nums.first()))
            value
        else
            0
    }
}
fun findSafeEquations2(readLines: List<String>): Long {
    return readLines.sumOf { l ->
        val value = l.split(":").first().toLong()
        val n = l.split(":").last().trim()
        val nums = n.split(" ").map { it.toLong() }
        if(canBeSolved2(value,nums.drop(1),nums.first()))
            value
        else
            0
    }
}

fun canBeSolved(value: Long, nums: List<Long>, currentValue: Long): Boolean {
    if(nums.isEmpty() && currentValue == value)
        return true
    else if(nums.isEmpty())
        return false

    if(currentValue + nums.reduce { acc, l -> acc+l } == value)
        return true

    return if(currentValue * nums.reduce { acc, i -> acc * i } == value){
        true

    }else {
        if(canBeSolved(value,nums.drop(1),(currentValue*nums.first())))
            true
        else if(canBeSolved(value,nums.drop(1),(currentValue+nums.first()))){
           true
        }else
            false
    }
}

fun part2(input: File) : Long = findSafeEquations2(input.readLines())

fun canBeSolved2(value: Long, nums: List<Long>, currentValue: Long): Boolean {
    if(nums.isEmpty() && currentValue == value)
        return true
    if(nums.isEmpty())
        return false
    if(currentValue > value)
        return false

    return if(canBeSolved2(value,nums.drop(1),(currentValue*nums.first())))
            true
        else if(canBeSolved2(value,nums.drop(1),(currentValue+nums.first()))){
            true
        }else if(canBeSolved2(value, nums.drop(1),(currentValue.toString()+nums.first().toString()).toLong())){
            true
        }else
            false
}