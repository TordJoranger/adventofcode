package main.aoc2024.day18

import main.common.functions.Point
import main.common.functions.bfsShortestPathWithRotation
import java.io.File

fun part1(input: File) : Long = memory(input.readLines())

fun memory(readLines: List<String>): Long {
  val obs = readLines.take(1024).map{ val split = it.split(",")
       Point(split[1].toInt(),split[0].toInt())
  }

    val grid = Array(71) { Array(71) { '.' } }
    obs.forEach { o -> grid[o.row][o.column] = '#' }
     val result =  bfsShortestPathWithRotation(grid.map { it.toList() }.toList(),Point(0,0),Point(70,70),0)
    return  result;
}

fun part2(input: File) : String = memory2(input.readLines())

fun memory2(readLines: List<String>): String {
    val obs = readLines.map{ val split = it.split(",")
        Point(split[1].toInt(),split[0].toInt())
    }
    val grid = Array(71) { Array(71) { '.' } }
    obs.take(1024).forEach { o -> grid[o.row][o.column] = '#' }
    for(i in 1025 until readLines.size){
        grid[obs[i].row][obs[i].column] = '#'
        val result =  bfsShortestPathWithRotation(grid.map { it.toList() }.toList(),Point(0,0),Point(70,70),0)
        if(result == -1L)
            return obs[i].toString()

    }
    return  "failed"
}
