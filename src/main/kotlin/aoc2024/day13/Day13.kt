package main.aoc2024.day13

import java.io.File


fun part1(input: File) : Long = findLeastPushes(input.readLines())

fun findLeastPushes(readLines: List<String>): Long {

    val windowned = readLines.windowed(3, step = 4)

   val sum = windowned.sumOf { m ->
        val solveSubstitution = solveSubstitution(m)
       (solveSubstitution.first *3 + solveSubstitution.second).toLong()
    }

    return sum
}

fun solveSubstitution(m: List<String>) : Pair<Int,Int> {
    val regex = "\\d+".toRegex()
    val line1 =  regex.findAll(m[0]).map { it.value.toInt() }
    val line2 =  regex.findAll(m[1]).map { it.value.toInt() }
    val line3 =  regex.findAll(m[2]).map { it.value.toInt() }

    val p = solveLinearEquations(
        line1.first().toDouble(),
        line2.first().toDouble(),
        line3.first().toDouble(),
        line1.last().toDouble(),
        line2.last().toDouble(),
        line3.last().toDouble()
        )

    if(p?.first?.rem(1.0)  == 0.0 && p.second.rem(1.0) ==0.0){
        return Pair(p.first.toInt(),p.second.toInt())
    }


   return Pair(0,0)




}

fun part2(input: File) : Long = findLeastPushes2(input.readLines())

fun findLeastPushes2(readLines: List<String>): Long {
    val windowned = readLines.windowed(3, step = 4)
    val sum = windowned.sumOf { m ->
        val solveSubstitution = solveSubstitution2(m)
        (solveSubstitution.first.toLong() *3 + solveSubstitution.second)
    }

    return sum
}

fun solveSubstitution2(m: List<String>) : Pair<Long,Long> {
    val regex = "\\d+".toRegex()
    val line1 =  regex.findAll(m[0]).map { it.value.toInt() }
    val line2 =  regex.findAll(m[1]).map { it.value.toInt() }
    val line3 =  regex.findAll(m[2]).map { it.value.toInt() }

    val p = solveLinearEquations(
        line1.first().toDouble(),
        line2.first().toDouble(),
        line3.first().toDouble()+10000000000000,
        line1.last().toDouble(),
        line2.last().toDouble(),
        line3.last().toDouble()+10000000000000
    )

    if(p?.first?.rem(1.0)  == 0.0 && p.second.rem(1.0) ==0.0){
        return Pair(p.first.toLong(),p.second.toLong())
    }

    return Pair(0,0)

}

fun solveLinearEquations(
    a1x: Double, b1y: Double, c1: Double,
    a2x: Double, b2y: Double, c2: Double
): Pair<Double, Double>? {
    val determinant = a1x * b2y - b1y * a2x
    if (determinant == 0.0) {
        return null
    }

    val A = (c1 * b2y - b1y * c2) / determinant
    val B = (a1x * c2 - c1 * a2x) / determinant

    return Pair(A, B)
}
