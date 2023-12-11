package aoc2023.day11

import org.junit.jupiter.api.Assertions
import java.io.File

class Day11Test {

    private val file = File("src/test/kotlin/aoc2023/day11/test11.txt")

    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2023.day11.part1(file)
        Assertions.assertEquals(374, result)
    }

    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2023.day11.part2(file)
        Assertions.assertEquals(1030, result)
    }
}