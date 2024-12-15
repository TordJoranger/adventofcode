package aoc2024.day14

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day14Test {

    private val file = File("src/test/kotlin/aoc2024/day14/test14.txt")

    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2024.day14.part1(file)
        Assertions.assertEquals(12, result)
    }

    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2024.day14.part2(file)
        Assertions.assertEquals(12, result)
    }



}