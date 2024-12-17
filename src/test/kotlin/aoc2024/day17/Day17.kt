package aoc2024.day17

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day17Test {

    private val file = File("src/test/kotlin/aoc2024/day17/test17.txt")

    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2024.day17.part1(file)
        Assertions.assertEquals("4,6,3,5,6,3,5,2,1,0", result)
    }


    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2024.day17.part2(file)
        Assertions.assertEquals("117440", result)
    }






}