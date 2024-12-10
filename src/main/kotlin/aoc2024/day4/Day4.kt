package main.aoc2024.day4

import java.io.File

fun part1(input: File) : Int = findXmas(input.readLines())

fun part2(input: File) : Int = findMasInX(input.readLines())

fun findXmas(readLines: List<String>): Int {
    val vertical = readLines[0].indices.map { x -> readLines.indices.map { y -> readLines[y][x] }.joinToString("") }
    val diagonalHorizontal = diagonal(readLines)
    val diagonalReversed = diagonal(readLines.map { it.reversed() })

return timesXMasOccurs(vertical)+ timesXMasOccurs(readLines) + timesXMasOccurs(diagonalHorizontal)+timesXMasOccurs(diagonalReversed)
}

private fun timesXMasOccurs(lines: List<String>) = lines.sumOf { s ->
    s.windowed(4){
        if (it == "XMAS" || it == "SAMX")
            1
        else
            0
    }.sum()
}

private fun timesMasXOccurs(lines: List<String>) : Int = lines.sumOf { s ->
    s.withIndex().windowed(3) { iv ->
        if (indexValuesIsMasOrSam(iv))
            1
        else
            0
    }.sum()
}

private fun indexValuesIsMasOrSam(iv: List<IndexedValue<Char>>) =
        iv.map { it.value.toString() }.reduce { acc, c -> acc + c } == "MAS" || iv.map { it.value.toString() }.reduce { acc, c -> acc + c } == "SAM"

private fun diagonal(lines: List<String>): List<String> {
    val width = lines[0].length
    val height = lines.size
    return (height * -1 until height).map { y ->
        (0 until width).map<Int, Any> { step ->
            if (y + step !in 0 until height || step !in 0 until width) " " else lines[y + step][step]
        }.joinToString("").trim()
    }
}

fun findMasInX(readLines: List<String>): Int {
    val diagonalHorizontal = diagonal(readLines)
    return timesMasXOccurs(diagonalHorizontal)
}





