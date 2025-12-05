package main.aoc2025.day4

import main.common.functions.Point
import java.io.File

fun part1(input: File) : Int = rollsOfPaper(input.readLines())

fun part2(input: File) : Int = rollsOfPaper2(input.readLines())

fun rollsOfPaper2(readLines: List<String>): Int {
    val matrix = readLines
        .map { it.toCharArray().toMutableList() }
        .toList()

    var returnCount = 0

    do {
       val count = (matrix.mapIndexed { x, list->  list.mapIndexed { y, char ->

            if(char == '@') {
                val removed = hasLessThan4Adj(Point(x,y),matrix)
                if (removed ==1) {
                    matrix[x][y] = '.'
                }
                removed
            } else
                0

        } }.flatMap { it }.sum())
        returnCount += count
    } while (count !=0 )
    return returnCount

}

fun rollsOfPaper(readLines: List<String>): Int {
    val matrix = readLines
        .map { it.toCharArray().toList() }
        .toList()

val count = matrix.mapIndexed { x, list->  list.mapIndexed { y, char ->

    if(char == '@')
        hasLessThan4Adj(Point(x,y),matrix)
    else
        0

} }.flatMap { it }.sum()



    return count

}

fun hasLessThan4Adj(point: Point, matrix: List<List<Char>>): Int {

    val directionMap = listOf(
        Point(0, 1),  // right → (row stays the same, col + 1)
        Point(1, 0),  // down → (row + 1, col stays the same)
        Point(0, -1), // left → (row stays the same, col - 1)
        Point(-1, 0), // up → (row - 1, col stays the same)
        Point(-1,-1),
        Point(-1,+1),
        Point(+1,+1),
        Point(+1,-1),

    )

  val rolls =  directionMap.count{p->
      val newRow = point.row+p.row
      val newCol = point.column+p.column
        if(newRow < 0 || newRow >= matrix.size)
            false
        else if(newCol < 0 || newCol >= matrix[0].size)
            false
        else if(matrix[newRow][newCol] == '@')
            true
        else
            false
        }
    return if(rolls > 3)
        0
    else
        1
}

