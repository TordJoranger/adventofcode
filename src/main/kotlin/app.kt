package main


import kotlin.system.measureTimeMillis

suspend fun main() {
     val day = 11
     val input = main.common.getInput(System.getenv("SessionCookie"), day)

     val elapsedP1 = measureTimeMillis {
          print("part1: ${main.aoc2023.day11.part1(input)}")
     }
     println(" in $elapsedP1 ms")

     val elapsedP2 = measureTimeMillis {
          print("part2: ${main.aoc2023.day11.part2(input)}")
     }
     println(" in $elapsedP2 ms")
}
