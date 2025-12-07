package aoc2025.day7

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day7Test {

    private val file = File("src/test/kotlin/aoc2025/day7/test7.txt")


    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2025.day7.part1(file)
        Assertions.assertEquals(21, result)
    }
}