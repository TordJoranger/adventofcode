package aoc2024.day9

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day9Test {

    private val file = File("src/test/kotlin/aoc2024/day9/test9.txt")

    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2024.day9.part1(file)
        Assertions.assertEquals(1928, result)
    }

    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2024.day9.part2(file)
        Assertions.assertEquals(2858, result)
    }


}