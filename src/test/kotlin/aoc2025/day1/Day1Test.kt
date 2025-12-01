package aoc2025.day1

import org.junit.jupiter.api.Assertions
import java.io.File
internal class Day1Test {

    private val file = File("src/test/kotlin/aoc2025/day1/test1.txt")
    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2025.day1.part2(file)
        Assertions.assertEquals(6, result)
    }
}
