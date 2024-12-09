package aoc2024.day7

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day7Test {

    private val file = File("src/test/kotlin/aoc2024/day7/test7.txt")

    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2024.day7.part1(file)
        Assertions.assertEquals(3749, result)
    }
    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2024.day7.part2(file)
        Assertions.assertEquals(11387, result)
    }
}