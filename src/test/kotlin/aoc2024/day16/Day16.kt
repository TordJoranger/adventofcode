package aoc2024.day16

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day16Test {

    private val file = File("src/test/kotlin/aoc2024/day16/test16.txt")

    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2024.day16.part1(file)
        Assertions.assertEquals(7036, result)
    }

    @org.junit.jupiter.api.Test
    fun part1b() {
        val result = main.aoc2024.day16.part1(File("src/test/kotlin/aoc2024/day16/test16b"))
        Assertions.assertEquals(11048, result)
    }

    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2024.day16.part2(file)
        Assertions.assertEquals(45, result)
    }

    @org.junit.jupiter.api.Test
    fun part2b() {
        val result = main.aoc2024.day16.part2(File("src/test/kotlin/aoc2024/day16/test16b"))
        Assertions.assertEquals(64, result)
    }
}