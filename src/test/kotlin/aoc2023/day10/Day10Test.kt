package aoc2023.day10

import org.junit.jupiter.api.Assertions
import java.io.File

class Day10Test {

    private val file = File("src/test/kotlin/aoc2023/day10/test10.txt")
    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2023.day10.part1(file)
        Assertions.assertEquals(8, result)
    }

    private val file2 = File("src/test/kotlin/aoc2023/day10/test10_2.txt")
    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2023.day10.part2(file2)
        Assertions.assertEquals(10, result)
    }

    private val file3 = File("src/test/kotlin/aoc2023/day10/test10_3.txt")
    @org.junit.jupiter.api.Test
    fun part2_2() {
        val result = main.aoc2023.day10.part2(file3)
        Assertions.assertEquals(4, result)
    }
}