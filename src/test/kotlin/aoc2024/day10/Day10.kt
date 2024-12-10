package aoc2024.day10

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day9Test {

    private val file = File("src/test/kotlin/aoc2024/day10/test10.txt")

    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2024.day10.part1(file)
        Assertions.assertEquals(36, result)
    }

    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2024.day10.part2(file)
        Assertions.assertEquals(81, result)
    }




}