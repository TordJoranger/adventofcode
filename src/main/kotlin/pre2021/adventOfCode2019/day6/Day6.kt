package main.pre2021.adventOfCode2019.day6

import java.io.File

fun day6() = File("src/pre2021.adventOfCode/pre2021.day6/pre2021.day6")
    .readText().split("\r\n\r\n")
    .map { it
        .split(Regex("\\s"))
        .filter { a -> a.isNotBlank()}
        .flatMap { s -> s.toCharArray().toList() }
        .toSet()
        .size
    }
    .sum()

fun day6part2() = File("src/pre2021.adventOfCode/pre2021.day6/pre2021.day6")
    .readText().split("\r\n\r\n")
    .mapIndexed { index, s ->
        s.split(Regex("\\s"))
        .filter { a -> a.isNotBlank()}
        .reduce{a,b -> returnCommon(a,b) }
        .length
    }

    .sum()

fun returnCommon(a: String, b: String): String {
  return  a.toCharArray().toList().intersect(b.toCharArray().toList()).joinToString ("")

}
