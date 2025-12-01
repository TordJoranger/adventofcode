package main.aoc2025.day1

import java.io.File
import kotlin.collections.count

fun part1(input: File) : Long = findZeroes(input.readLines())

fun findZeroes(readLines: List<String>): Long {
   var dialAt = 50L

   val count = readLines.count {
        val first = it.first()
        val num = it.takeLast(it.length-1).toLong()
        if(first == 'L'){
            dialAt = dialAt.minus(num) % 100
        }else if (first == 'R'){
            dialAt = dialAt.plus(num) % 100
        }
        if (dialAt == 0L){
             true
        }else{
            false
        }
    }

    return count.toLong()
}

fun part2(input: File) : Long = findZeroes2(input.readLines())

fun findZeroes2(readLines:  List<String>): Long {
    var dialAt = 50L

    val count = readLines.sumOf {
        val first = it.first()
        val num = it.takeLast(it.length-1).toLong()
        if(first == 'L'){
            var count = 0
            if(dialAt == 0L){
                count--
            }

            var click = dialAt.minus(num)
            dialAt = dialAt.minus(num).mod(100L)
            while (click  < 1){
                count++
                click +=100
            }
            count

        }else {
            var click = dialAt.plus(num)
            dialAt = dialAt.plus(num).mod(100L)
            var count = 0
            while (click  >= 100){
                count++
                click -= 100
            }
            count
        }
    }

    return count.toLong()
}
