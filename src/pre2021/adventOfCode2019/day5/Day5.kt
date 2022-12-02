package pre2021.adventOfCode2019.day5


import java.io.File

fun day5() = highestBoardingPass(File("src/pre2021.adventOfCode/day5/day5").readLines())

fun highestBoardingPass(readLines: List<String>): Int {

    val numbers = readLines.map { s -> calculateBinary(s) }.sorted()

    //part1
   // return numbers.max()!!

    //part 2
    return numbers.filterIndexed { index, i ->
            index != numbers.size-1
                    &&
            numbers[index+1] != i+1
        }.first() + 1

}


fun calculateScore(l: String): Int {
    return calculateBinary(l.substring(0,7)) * 8 + calculateBinary(l.substring(7))
}

fun calculateBinary(s: String): Int {
    val binary = s.map { b-> when(b) {
        'B' -> "1"
        'R'-> "1"
        '1'-> "1"
        else -> "0"
    } }.joinToString("")
    return Integer.parseInt(binary,2)
}
