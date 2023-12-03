package aoc2023.day3

import org.junit.jupiter.api.Assertions
import java.io.File

class Day3Test {


    internal class Day3Test {

        private val file = File("src/test/kotlin/aoc2023/day3/test3.txt")
        @org.junit.jupiter.api.Test
        fun part1() {
            val result = main.aoc2023.day3.part1(file)
            Assertions.assertEquals(4361, result)
        }

        @org.junit.jupiter.api.Test
        fun part2() {
            val result = main.aoc2023.day3.part2(file)
            Assertions.assertEquals(467835, result)
        }

    }
}