package aoc2024.day12

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day12Test {

    private val file = File("src/test/kotlin/aoc2024/day12/test12.txt")

    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2024.day12.part1(file)
        Assertions.assertEquals(1930, result)
    }
    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2024.day12.part2(file)
        Assertions.assertEquals(80, result)
    }

}