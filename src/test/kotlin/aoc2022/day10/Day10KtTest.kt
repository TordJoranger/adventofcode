package aoc2022.day10

import org.junit.jupiter.api.Assertions
import java.io.File

internal class Day10KtTest {

    private val file = File("src/test/kotlin/aoc2022/day10/test10.txt")
    @org.junit.jupiter.api.Test
    fun part1() {
        val result = main.aoc2022.day10.part1(file)
        Assertions.assertEquals(13140, result)
    }

    @org.junit.jupiter.api.Test
    fun part2() {
        val result = main.aoc2022.day10.part2(file)
        Assertions.assertEquals("##..##..##..##..##..##..##..##..##..##..\n" +
                "###...###...###...###...###...###...###.\n" +
                "####....####....####....####....####....\n" +
                "#####.....#####.....#####.....#####.....\n" +
                "######......######......######......####\n" +
                "#######.......#######.......#######.....", result)
    }


}