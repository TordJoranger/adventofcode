package aoc2022.day8

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class Day8KtTest {

    private val file = File("src/test/kotlin/aoc2022/day8/test8.txt")
    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2022.day8.part1(file)
        assertEquals(21,result)
    }

    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2022.day8.part2(file)
        assertEquals(8,result)
    }
}