package aoc2024.day19

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day19Test {

    private val file = File("src/test/kotlin/aoc2024/day19/test19.txt")

    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2024.day19.part1(file)
        Assertions.assertEquals(6, result)
    }

    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2024.day19.part2(file)
        Assertions.assertEquals(16, result)
    }

}