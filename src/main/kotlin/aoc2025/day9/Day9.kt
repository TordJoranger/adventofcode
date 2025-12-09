package main.aoc2025.day9

import main.common.functions.Point
import java.io.File
import kotlin.math.absoluteValue

fun part1(input: File): Long = findRedRectangle(input.readLines())
fun part2(input: File): Long = findRedRectangle2(input.readLines())

fun findRedRectangle2(readLines: List<String>): Long {

    val points = readLines.map {
        val split = it.split(",")
        Point(split[1].toInt(), split[0].toInt())
    }

    return points.fold(0L) { acc, point ->
        val max = points.filter { it != point }
            .maxOf {
                if(it.row < point.row)
                    findArea2(it, point, points.map { it.row }, points.map { it.column })
                else
                    findArea2(point, it, points.map { it.row }, points.map { it.column })
                 }
        if (max > acc)
            max
        else
            acc
    }
}

fun findArea2(
    pA: Point,
    pB: Point,
    rows:  List<Int>,
    columns: List<Int>
) : Long{

   val valid = (pA.row .. pB.row).all {
        columns.any{col -> col < pA }
    }

    val validCol = (pA.column .. pB.column).all {
        columns.any{ row -> row in pA.row .. pB.row }
    }
    if(!validCol || !valid)
        return 0L

    val distanceRows = (pA.row - pB.row).absoluteValue.toLong()+1
    val distanceColumns = (pA.column - pB.column).absoluteValue.toLong()+1
    val sum = distanceRows  * distanceColumns


    return sum
}

fun findRedRectangle(readLines: List<String>): Long {
    return readLines.fold(0L) {acc, a ->
        val max = readLines.maxOf { findArea(it,a) }
        if(max > acc)
            max
        else
            acc
    }
}

fun findArea(a: String, b: String) : Long {
    val pA = a.split(",").map { it.toInt() }
    val pB = b.split(",").map { it.toInt() }
    val lng = ((pA[0] - pB[0]).absoluteValue.toLong()+1) * ((pA[1] - pB[1]).absoluteValue+1)
    return lng
}
