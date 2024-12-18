package main.common.functions

import java.util.*
import kotlin.collections.HashMap

data class Point(val row: Int, val column: Int)
data class State(val row: Int, val column: Int, val direction: Char, val cost: Long) : Comparable<State> {
    override fun compareTo(other: State): Int = this.cost.toInt() - other.cost.toInt()
}

fun bfsShortestPathWithRotation(grid: List<List<Char>>, start: Point, end: Point, rotationCost: Int): Long {
    val directionMap = mapOf(
        '>' to Point(0, 1),  // right → (row stays the same, col + 1)
        'v' to Point(1, 0),  // down → (row + 1, col stays the same)
        '<' to Point(0, -1), // left → (row stays the same, col - 1)
        '^' to Point(-1, 0)  // up → (row - 1, col stays the same)
    )

    val rows = grid.size
    val cols = grid[0].size
    val visited = HashMap<Triple<Int, Int, Char>, Long>()
    val pq = PriorityQueue<State>()

    pq.add(State(start.row, start.column, '>', 0)) // Start with the given direction and cost 0
    visited[Triple(start.row,start.column,'>')] = 0

    while (pq.isNotEmpty()) {
        val current = pq.poll()

        // Check if we reached the endpoint
        if (current.row == end.row && current.column == end.column) {
            return current.cost
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
            if (newRow in 0 until rows && newColumn in 0 until cols &&
                grid[newRow][newColumn] != '#' && (newVisitedValue == null || newCost < newVisitedValue)
            ) {
                visited[Triple(newRow,newColumn,dir.key)] = newCost
                pq.add(State(newRow, newColumn, dir.key, newCost))
            }
        }
    }

    return -1 // No path exists
}