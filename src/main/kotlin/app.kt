package main

import java.io.File
import kotlin.system.measureTimeMillis

suspend fun main() {
     val day = 6
     val input = main.common.getInput(System.getenv("SessionCookie"), day)

    // val i = File("src/main/kotlin/common/inputs/aoc_2022_day05_large_input-2.txt")
     val elapsedP1 = measureTimeMillis {
          print("part1: ${main.aoc2022.day6.part1(input)}")
     }
     println(" in $elapsedP1 ms")

     val elapsedP2 = measureTimeMillis {
          print("part2: ${main.aoc2022.day6.part2(input)}")
     }
     println(" in $elapsedP2 ms")


}
