package aoc2022.day1

import java.io.File

fun run() = getTop3MostCalories(File("src/aoc2022/pre2021.day1/pre2021.day1.txt").readText().split("\r\n\r\n"))
fun getMostCalories(list: List<String>): Int {
return list.map{ l -> l.split("\r\n").sumBy { s -> s.toInt() } }.max()!!.toInt()
}
fun getTop3MostCalories(list: List<String>): Int {
    return list.map{ l -> l.split("\r\n").sumBy { s -> s.toInt() } }.sortedDescending().take(3).sum()
}
