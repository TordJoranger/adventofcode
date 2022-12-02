package main.aoc2022.day2
import java.io.File
fun run(input: File) : String = day2(input.readLines())

private fun day2(list: List<String>): String =
    "part1: ${ part1(list) }" +
    "\r\n\r\n" +
    "part2: ${ part2(list) }"


private fun part1(list: List<String>): Int = list.sumBy { s ->
    when (s) {
        "A X" -> 3
        "A Y" -> 4
        "A Z" -> 8
        "B X" -> 1
        "B Y" -> 5
        "B Z" -> 9
        "C X" -> 2
        "C Y" -> 6
        "C Z" -> 7
        else -> throw Exception("you fucked up")
    }
}
private fun part2(list: List<String>): Int = list.sumBy { s ->
    when (s) {
        "A X" -> 4
        "A Y" -> 8
        "A Z" -> 3
        "B X" -> 1
        "B Y" -> 5
        "B Z" -> 9
        "C X" -> 7
        "C Y" -> 2
        "C Z" -> 6
        else -> throw Exception("you fucked up")
    }
}



