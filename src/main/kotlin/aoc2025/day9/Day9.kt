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


        val max = points.filter { it != point }.filter { allGreenAndRed(it, point, points) }
            .maxOf { findArea2(it, point) }
        if (max > acc)
            max
        else
            acc
    }
}

fun allGreenAndRed(a: Point, b: Point, points: List<Point>): Boolean {

    val rows = points.groupBy { it.column }

    val columns = points.groupBy { it.row }

   val valid1 = (a.column .. b.column).all { c ->
        val r = rows[c]
       r.all { r -> r in  }


       true
    }

    val valid2 = (a.row .. b.row).all { c ->
        val r = columns[c]!!
        r[0].column in a.column .. b.column
    }
    return valid1 && valid2

}

fun findArea2(
    pA: Point,
    pB: Point
) : Long{



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
