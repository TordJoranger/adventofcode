package aoc2023.day8

import org.junit.jupiter.api.Assertions
import java.io.File

class Day8Test {

    private val file = File("src/test/kotlin/aoc2023/day8/test8.txt")
    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2023.day8.part1(file)
        Assertions.assertEquals(6, result)
    }

    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2023.day8.part2(file)
        Assertions.assertEquals(6, result)
    }

}