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

private fun timesMasXOccurs(lines: List<String>, reverseLines: List<String>) : Int = lines.mapIndexed { index, s ->
    s.withIndex().windowed(3){ iv ->
        if (indexValuesIsMasOrSam(iv))
            1
        else
            0
    }.sum()
}.sum()

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
    val vertical = readLines[0].indices.map { x -> readLines.indices.map { y -> readLines[y][x] }.joinToString("") }
    val diagonalHorizontal = diagonal(readLines)
    val diagonalReversed = diagonal(readLines.map { it.reversed() })

    return timesMasXOccurs(diagonalHorizontal,diagonalReversed)

    val mRegex = "M".toRegex()
    val sRegex = "S".toRegex()
    val ms = readLines.mapIndexed { index, s ->
        mRegex.findAll(s).map { it.range.first }.map { m->
            isMasInXByM(readLines,m,index)
        }.sum()
    }.sum()
  val ss =
    readLines.mapIndexed { index, s ->
        sRegex.findAll(s).map { it.range.first }.map { m->
            isMasInXByS(readLines,m,index)
        }.sum()
    }.sum()
return ss+ms
}

fun isMasInXByS(readLines: List<String>, XIndex: Int, yAxis: Int): Int {
    val matrix: Array<Array<Char>> = Array(readLines.size) { outer -> Array(readLines.first().length, init = { i -> readLines[outer].toCharArray()[i]}) }

    var sum = 0
    if(canGo2Right(XIndex, matrix) && canGo2Down(yAxis, readLines) &&  matrix[yAxis+1][XIndex+1] == 'A' && matrix[yAxis+2][XIndex+2] == 'M'
            && (matrix[yAxis+2][XIndex] == 'M' && matrix[yAxis+1][XIndex+1] == 'A' && matrix[yAxis][XIndex+2] == 'S'
                    || matrix[yAxis+2][XIndex] == 'S' && matrix[yAxis+1][XIndex+1] == 'A' && matrix[yAxis][XIndex+2] == 'M'))
        sum++

    return sum
}

fun isMasInXByM(readLines: List<String>, XIndex: Int, yAxis: Int): Int {
    val matrix: Array<Array<Char>> = Array(readLines.size) { outer -> Array(readLines.first().length, init = { i -> readLines[outer].toCharArray()[i]}) }

    var sum = 0
    if(canGo2Right(XIndex, matrix) && canGo2Down(yAxis, readLines) &&  matrix[yAxis+1][XIndex+1] == 'A' && matrix[yAxis+2][XIndex+2] == 'S'
            && (matrix[yAxis+2][XIndex] == 'M' && matrix[yAxis+1][XIndex+1] == 'A' && matrix[yAxis][XIndex+2] == 'S'
                || matrix[yAxis+2][XIndex] == 'S' && matrix[yAxis+1][XIndex+1] == 'A' && matrix[yAxis][XIndex+2] == 'M'))
        sum++

    return sum
}


fun isXmas(readLines: List<String>, XIndex: Int, yAxis: Int): Int {
    val matrix: Array<Array<Char>> = Array(readLines.size) { outer -> Array(readLines.first().length, init = { i -> readLines[outer].toCharArray()[i]}) }

    var sum = 0
    if(canGoDown(yAxis, readLines) && matrix[yAxis+1][XIndex] == 'M' && matrix[yAxis+2][XIndex] == 'A' && matrix[yAxis+3][XIndex] == 'S') //DOWN
        sum++
    if(canGoRight(XIndex, matrix) && matrix[yAxis][XIndex+1] == 'M' && matrix[yAxis][XIndex+2] == 'A' && matrix[yAxis][XIndex+3] == 'S') //Right
        sum++
    if(canGoRight(XIndex, matrix) && canGoDown(yAxis, readLines) &&  matrix[yAxis+1][XIndex+1] == 'M' && matrix[yAxis+2][XIndex+2] == 'A' && matrix[yAxis+3][XIndex+3] == 'S') //DOWN RIGHT
        sum++
    if(canGoLeft(XIndex) && canGoDown(yAxis, readLines) &&  matrix[yAxis+1][XIndex-1] == 'M' && matrix[yAxis+2][XIndex-2] == 'A' && matrix[yAxis+3][XIndex-3] == 'S') //DOWN LEFT
        sum++
    if(canGoLeft(XIndex) && matrix[yAxis][XIndex-1] == 'M' && matrix[yAxis] [XIndex-2]== 'A' && matrix[yAxis][XIndex-3] == 'S') //LEFT
        sum++
    if(canGoLeft(XIndex) && canGoUp(yAxis) &&  matrix[yAxis-1][XIndex-1] == 'M' && matrix[yAxis-2][XIndex-2] == 'A' && matrix[yAxis-3][XIndex-3] == 'S') //UP LEFT
        sum++
    if(canGoUp(yAxis) && matrix[yAxis-1][XIndex] == 'M' && matrix[yAxis-2][XIndex]== 'A' && matrix[yAxis-3][XIndex] == 'S')//UP
        sum++
    if(canGoUp(yAxis) && canGoRight(XIndex,matrix) && matrix[yAxis-1][XIndex+1] == 'M' && matrix[yAxis-2][XIndex+2] == 'A' && matrix[yAxis-3][XIndex+3] == 'S')//UP RIGHT
        sum++

    return sum
}

private fun canGoRight(XIndex: Int, matrix: Array<Array<Char>>) =
        XIndex < matrix[0].size - 3
private fun canGo2Right(XIndex: Int, matrix: Array<Array<Char>>) =
        XIndex < matrix[0].size - 2

private fun canGoDown(yAxis: Int, readLines: List<String>) = yAxis < readLines.size - 3

private fun canGo2Down(yAxis: Int, readLines: List<String>) = yAxis < readLines.size - 2

private fun canGoLeft(XIndex: Int) = XIndex - 3 >= 0

private fun canGoUp(yAxis: Int) = yAxis - 3 >= 0




