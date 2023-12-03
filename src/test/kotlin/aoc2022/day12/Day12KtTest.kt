package aoc2022.day12

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class Day12KtTest {

    private val file = File("src/test/kotlin/aoc2022/day12/test12.txt")

    @Test
    fun part1() {
        val result = main.aoc2022.day12.part1(file)
        assertEquals(31, result)
    }

    @Test
    fun part2() {
        val result = main.aoc2022.day12.part2(file)
        assertEquals(0, result)
    }
}