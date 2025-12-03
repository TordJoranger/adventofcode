package aoc2025.day3

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day3Test {

    private val file = File("src/test/kotlin/aoc2025/day3/test3.txt")

    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2025.day3.part1(file)
        Assertions.assertEquals(357, result)
    }
    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2025.day3.part2(file)
        Assertions.assertEquals(3121910778619, result)
    }
}