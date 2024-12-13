package aoc2024.day13

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day13Test {

    private val file = File("src/test/kotlin/aoc2024/day13/test13.txt")

    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2024.day13.part1(file)
        Assertions.assertEquals(480, result)
    }

    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2024.day13.part2(file)
        Assertions.assertEquals(81, result)
    }


}