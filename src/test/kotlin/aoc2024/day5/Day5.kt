package aoc2024.day4

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day5Test {

    private val file = File("src/test/kotlin/aoc2024/day5/test5.txt")
    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2024.day5.part1(file)
        Assertions.assertEquals(143, result)
    }

    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2024.day5.part2(file)
        Assertions.assertEquals(123, result)
    }
}