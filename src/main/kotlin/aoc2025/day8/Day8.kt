package main.aoc2025.day8


import java.io.File
import java.util.Collections.addAll
import kotlin.math.pow
import kotlin.math.sqrt

fun part1(input: File, i: Int = 1000): Long = findCircuits(input.readLines(), i)

fun findCircuits(readLines: List<String>, i: Int): Long {

    val orderedList = mutableListOf<Pair<Pair<Point, Point>, Double>>()


    val points = readLines.map {
        val coordinates = it.split(",")
        Point(coordinates[0].toDouble(), coordinates[1].toDouble(), coordinates[2].toDouble())
    }

    points.forEachIndexed { index1, point ->
        points.drop((1 + index1)).forEachIndexed { index2, point2 ->
            val distance = distance(point, point2)
            if (orderedList.size < i)
                orderedList.add(Pair(Pair(point, point2), distance))
            else if (distance < orderedList.last().second) {
                orderedList.removeLast()
                orderedList.add(Pair(Pair(point, point2), distance))
            }
            orderedList.sortBy { it.second }
        }
    }
    val circuits = points.map { p -> mutableSetOf(p) }.toMutableSet()

    orderedList.forEach { pair ->
        val first = pair.first.first
        val second = pair.first.second

        circuits.remove(mutableSetOf(first))
        circuits.remove(mutableSetOf(second))

        val added = circuits.any { list ->
            val containsFirst = list.contains(first)
            val containsSecond = list.contains(second)
            if(containsFirst && containsSecond)
                true
            else if (containsFirst) {
                val toMerge = circuits.singleOrNull { it != list && it.contains(second) }
                if(toMerge != null){
                    list.addAll(toMerge)
                     circuits.removeIf { it.containsAll(toMerge) && it.size == toMerge.size }
                } else {
                    list.add(second)
                }
                true
            } else if (containsSecond) {
                val toMerge = circuits.singleOrNull { it != list && it.contains(first) }
                if(toMerge != null){
                    list.addAll(toMerge)
                   circuits.removeIf { it.containsAll(toMerge) && it.size == toMerge.size }
                } else {
                    list.add(first)
                }
                true
            } else {
                false
            }
        }
        if (!added) {
            circuits.add(mutableSetOf(first, second))
        }
    }

    val sortedByDescending = circuits.sortedByDescending { it.size }
    return sortedByDescending.take(3).map { it.size }.reduce { a, b -> a * b }.toLong()
}


fun part2(input: File, i: Int = 10000): Long = findCircuits2(input.readLines(), i)


fun findCircuits2(readLines: List<String>, i: Int): Long {

    val orderedList = mutableListOf<Pair<Pair<Point, Point>, Double>>()


    val points = readLines.map {
        val coordinates = it.split(",")
        Point(coordinates[0].toDouble(), coordinates[1].toDouble(), coordinates[2].toDouble())
    }
    val circuits = points.map { p -> mutableSetOf(p) }.toMutableSet()
    points.forEachIndexed { index1, point ->
        points.drop((1 + index1)).forEachIndexed { index2, point2 ->
            val distance = distance(point, point2)
            if (orderedList.size < i)
                orderedList.add(Pair(Pair(point, point2), distance))
            else if (distance < orderedList.last().second) {
                orderedList.removeLast()
                orderedList.add(Pair(Pair(point, point2), distance))
            }
            orderedList.sortBy { it.second }
        }
    }



    orderedList.forEach { pair ->
        val first = pair.first.first
        val second = pair.first.second

        circuits.remove(mutableSetOf(first))
        circuits.remove(mutableSetOf(second))

        val added = circuits.any { list ->
            val containsFirst = list.contains(first)
            val containsSecond = list.contains(second)
            if(containsFirst && containsSecond)
                true
            else if (containsFirst) {
                val toMerge = circuits.singleOrNull { it != list && it.contains(second) }
                if(toMerge != null){
                    list.addAll(toMerge)
                    circuits.removeIf { it.containsAll(toMerge) && it.size == toMerge.size }
                } else {
                    list.add(second)
                }
                true
            } else if (containsSecond) {
                val toMerge = circuits.singleOrNull { it != list && it.contains(first) }
                if(toMerge != null){
                    list.addAll(toMerge)
                    circuits.removeIf { it.containsAll(toMerge) && it.size == toMerge.size }
                } else {
                    list.add(first)
                }
                true
            } else {
                false
            }
        }
        if (!added) {
            circuits.add(mutableSetOf(first, second))
        }

        if(circuits.size ==1)
            return first.x.toLong()*second.x.toLong()
    }

    val sortedByDescending = circuits.sortedByDescending { it.size }
    return sortedByDescending.take(3).map { it.size }.reduce { a, b -> a * b }.toLong()
}

data class Point(val x: Double, val y: Double, val z: Double)


fun distance(pointA: Point, pointB: Point): Double {
    return sqrt((pointA.x - pointB.x).pow(2) + (pointA.y - pointB.y).pow(2) + (pointA.z - pointB.z).pow(2))
}


