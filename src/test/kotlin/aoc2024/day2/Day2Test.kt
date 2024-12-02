package aoc2024.day2

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day2Test {

    private val file = File("src/test/kotlin/aoc2024/day2/test2.txt")
    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2024.day2.part2(file)
        Assertions.assertEquals(4, result)
    }

}