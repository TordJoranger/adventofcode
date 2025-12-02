package aoc2025.day2

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day2Test {

    private val file = File("src/test/kotlin/aoc2025/day2/test2.txt")

    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2025.day2.part1(file)
        Assertions.assertEquals(1227775554, result)
    }
    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2025.day2.part2(file)
        Assertions.assertEquals(4174379265, result)
    }
}
