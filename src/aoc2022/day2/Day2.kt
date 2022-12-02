package aoc2022.day2
import java.io.File
fun run() = calculateRPS(File("src/aoc2022/day2/day2.txt").readLines())
fun calculateRPS(list: List<String>): Int {
    return list.sumBy { s-> calculateRPSScoreP2(s) }
}
fun calculateRPSScoreP1(s: String): Int {
    return when(s) {
        "A X" -> 4
        "A Y" -> 8
        "A Z" -> 3
        "B X" -> 1
        "B Y" -> 5
        "B Z" -> 9
        "C X" -> 7
        "C Y" -> 2
        "C Z" -> 6
        else -> 0
    }
}
fun calculateRPSScoreP2(s: String): Int {
   return when(s) {
        "A X" -> 3
        "A Y" -> 4
        "A Z" -> 8
        "B X" -> 1
        "B Y" -> 5
        "B Z" -> 9
        "C X" -> 2
        "C Y" -> 6
        "C Z" -> 7
       else -> 0
   }
}
