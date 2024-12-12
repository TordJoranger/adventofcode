package main.aoc2024.day11

import java.io.File

fun part1(input: File) : Long = countStones(25,input.readText())
fun part2(input: File) : Long = countStones(75,input.readText())
val knownNodes = hashMapOf<Pair<Long,Int>,Long>()
fun countStones(blinks: Int, readLines: String): Long {

   return readLines.split(" ").map { it.toLong() }.sumOf { i ->
      countStone(i, blinks)
    }
}

fun countStone(i: Long, blinks: Int): Long {
    val pair = Pair(i, blinks)
    val any = knownNodes[pair]
    return any
        ?: if(blinks == 0) {
            1
        } else if(i== 0L) {
            val zero = countStone(1, blinks - 1)
            knownNodes[Pair(i, blinks)] = zero
            zero
        } else {
            val str = i.toString()
            if(str.length % 2 == 0){
                val split = countStone(str.take(str.length/2).toLong(),blinks-1) +
                        countStone(str.takeLast(str.length/2).toLong(),blinks-1)
                knownNodes[Pair(i, blinks)] = split
                split
            }else{
                val v2024 = countStone(i*2024,blinks-1)
                knownNodes[Pair(i, blinks)] = v2024
                v2024
            }
        }
}
