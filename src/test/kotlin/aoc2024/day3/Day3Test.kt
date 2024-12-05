package aoc2024.day3

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day3Test {

    private val file = File("src/test/kotlin/aoc2024/day3/test3.txt")
    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2024.day3.part2(file)
        Assertions.assertEquals(48, result)
    }

}