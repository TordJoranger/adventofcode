package aoc2022.day11

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class Day11KtTest {

    private val file = File("src/test/kotlin/aoc2022/day11/test11.txt")
    @Test
    fun part1() {
        val result = main.aoc2022.day11.part1(file)
        assertEquals(10605, result)
    }

    @Test
    fun part2() {
        val result = main.aoc2022.day11.part2(file)
        assertEquals(2713310158, result)
    }
}