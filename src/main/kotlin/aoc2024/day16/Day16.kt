package main.aoc2024.day16

import java.io.File
import java.util.*
import kotlin.collections.HashMap

fun part1(input: File) : Long = maze(input.readLines(),false)
fun part2(input: File) : Long = maze(input.readLines(),true)

fun maze(readLines: List<String>, part2: Boolean): Long {
    val matrix = readLines
        .map { it.toCharArray().toList() }
        .toList()
    val startPos = Point(matrix.size-2,1)
    val distance = bfsShortestPathWithRotation(matrix,startPos, Point(1,matrix[0].size-2),1000)

    if(!part2)
        return distance.first


    return  distance.second.size.toLong()

}

data class Point(val row: Int, val column: Int)
data class State(val row: Int, val column: Int, val direction: Char, val cost: Long, val previous: List<Point>) : Comparable<State> {
    val path : List<Point> = previous.plus(Point(row,column))
    override fun compareTo(other: State): Int = this.cost.toInt() - other.cost.toInt()
}

fun bfsShortestPathWithRotation(grid: List<List<Char>>, start: Point, end: Point, rotationCost: Int): Pair<Long,Set<Point>> {
    val directionMap = mapOf(
        '>' to Point(0, 1),
        'v' to Point(1, 0),
        '<' to Point(0, -1),
        '^' to Point(-1, 0)
    )

    var toReturn = 0L
    val rows = grid.size
    val cols = grid[0].size
    val visited = HashMap<Triple<Int, Int, Char>, Long>()
    val pq = PriorityQueue<State>()

    pq.add(State(start.row, start.column, '>', 0, listOf(start)))
    visited[Triple(start.row,start.column,'>')] = 0
    val pathMap = hashSetOf(start)

    while (pq.isNotEmpty()) {
        val current = pq.poll()
        // Check if we reached the endpoint
        if (current.row == end.row && current.column == end.column) {
            toReturn = current.cost
            directionMap.keys.forEach { key ->
                visited[Triple(current.row,current.column,key)] = current.cost
            }

            pathMap.addAll(current.path)
        }

        // If already visited with a lower cost, skip
        val visitedValue = visited[Triple(current.row, current.column, current.direction)]
        if (visitedValue != null &&current.cost > visitedValue) continue

        // Explore all possible directions
        for (dir in directionMap) {
            val newRow = current.row + dir.value.row
            val newColumn = current.column + dir.value.column

            val additionalCost = if (current.direction != dir.key) rotationCost else 0
            val newCost = current.cost + 1 + additionalCost

            // Check boundaries, walls, and if the cost is better
            val newVisitedValue = visited[Triple(newRow, newColumn, dir.key)]
            if (newRow in 0 until rows && newRow in 0 until cols &&
                grid[newRow][newColumn] != '#' && (newVisitedValue == null || newCost <= newVisitedValue)
            ) {
                visited[Triple(newRow,newColumn,dir.key)] = newCost
                pq.add(State(newRow, newColumn, dir.key, newCost,current.path))
            }
        }
    }
    return Pair(toReturn,pathMap)
}



