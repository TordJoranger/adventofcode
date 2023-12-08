package aoc2023.day6

import org.junit.jupiter.api.Assertions
import java.io.File

class Day6Test {

    private val file = File("src/test/kotlin/aoc2023/day6/test6.txt")
    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2023.day6.part1(file)
        Assertions.assertEquals(288, result)
    }


}