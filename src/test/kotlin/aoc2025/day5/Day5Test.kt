package aoc2025.day5

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day5Test {

    private val file = File("src/test/kotlin/aoc2025/day5/test5.txt")


    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2025.day5.part2(file)
        Assertions.assertEquals(16, result)
    }
}