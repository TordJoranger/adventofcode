package aoc2024.day4

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day2Test {

    private val file = File("src/test/kotlin/aoc2024/day4/test4.txt")
    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2024.day4.part1(file)
        Assertions.assertEquals(18, result)
    }

    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2024.day4.part2(file)
        Assertions.assertEquals(9, result)
    }

}