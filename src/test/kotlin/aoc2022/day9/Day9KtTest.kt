package aoc2022.day9

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class Day9KtTest {

    private val file = File("src/test/kotlin/aoc2022/day9/test9.txt")
    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2022.day9.part1(file)
        assertEquals(13,result)
    }

    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2022.day9.part2(file)
        assertEquals(8,result)
    }

}

