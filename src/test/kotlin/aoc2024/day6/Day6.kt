package aoc2024.day6



import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day6Test {

    private val file = File("src/test/kotlin/aoc2024/day6/test6.txt")
    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2024.day6.part1(file)
        Assertions.assertEquals(41, result)
    }

    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2024.day6.part2(file)
        Assertions.assertEquals(6, result)
    }

}