package main.aoc2025.day7

import main.common.functions.Point
import main.common.functions.vertical
import java.io.File

fun part1(input: File) : Long = findSplits(input.readLines())
fun part2(input: File) : Long = findSplits2(input.readLines())

fun findSplits(readLines: List<String>): Long {
    val visited = HashMap<Point, Long>()
    val verticals = vertical(readLines)

    return findSplit(verticals,visited, 70, 1)
}

fun findSplit(verticals: List<String>, visited: HashMap<Point, Long>, startIndex: Int, progress: Int) : Long {

    val string = verticals[startIndex].substring(progress)
    val split = string.indices.firstOrNull{  string[it] == '^'  }

    if(split != null){
        val point = Point(startIndex, progress + split)
        if(visited.contains(point))
            return 0

        val toReturn = findSplit(verticals, visited, startIndex - 1, progress + split + 1) +
                findSplit(verticals, visited, startIndex + 1, progress + split + 1) + 1
        visited[Point(startIndex,progress+split)] = toReturn
        return toReturn
    }
    else
        return 0
}

fun findSplits2(readLines: List<String>): Long {
    val visited = HashMap<Point, Long>()
    val verticals = vertical(readLines)
    return findSplit2(verticals,visited, 70, 1)+1
}

fun findSplit2(verticals: List<String>, visited: HashMap<Point, Long>, startIndex: Int, progress: Int) : Long {

    val string = verticals[startIndex].substring(progress)
    val split = string.indices.firstOrNull{  string[it] == '^'  }
    if(split != null){
        val point = Point(startIndex, progress + split)
        if(visited.contains(point))
            return visited.getValue(point)

        val toReturn = findSplit2(verticals, visited, startIndex - 1, progress + split + 1) +
                findSplit2(verticals, visited, startIndex + 1, progress + split + 1) + 1
        visited[Point(startIndex,progress+split)] = toReturn
        return toReturn
    }
    else
        return 0
}
