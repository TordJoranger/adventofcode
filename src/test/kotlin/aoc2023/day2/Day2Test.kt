package aoc2023.day2

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day2Test {

    private val file = File("src/test/kotlin/aoc2023/day2/test2.txt")
    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2023.day2.part1(file)
        Assertions.assertEquals(8, result)
    }

}