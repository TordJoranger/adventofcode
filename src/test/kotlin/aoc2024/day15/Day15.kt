package aoc2024.day15

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day15Test {

    private val file = File("src/test/kotlin/aoc2024/day15/test15.txt")

    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2024.day15.part1(file)
        Assertions.assertEquals(10092, result)
    }

    private val file2 = File("src/test/kotlin/aoc2024/day15/test15-2.txt")
    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2024.day15.part2(file2)
        Assertions.assertEquals(9021, result)
    }




}