package aoc2024.day1


import org.junit.jupiter.api.Assertions
import java.io.File
internal class Day1Test {

    private val file = File("src/test/kotlin/aoc2024/day1/test1.txt")
    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2024.day1.part1(file)
        Assertions.assertEquals(11, result)
    }
}
