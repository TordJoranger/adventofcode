package aoc2023.day7

import org.junit.jupiter.api.Assertions
import java.io.File

class Day7Test {

    private val file = File("src/test/kotlin/aoc2023/day7/test7.txt")
    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2023.day7.part1(file)
        Assertions.assertEquals(6440, result)
    }


    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2023.day7.part2(file)
        Assertions.assertEquals(5905, result)
    }



}