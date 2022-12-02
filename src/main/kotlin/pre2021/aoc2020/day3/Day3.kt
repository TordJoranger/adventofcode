package main.pre2021.aoc2020.day3

import main.common.Vector
import kotlin.math.abs

fun day3part1() : Int{
    val lists1 = getVectors("src/pre2021.day3.txt/pre2021.day3.txt.1.txt")
    val lists2 = getVectors("src/pre2021.day3.txt/pre2021.day3.txt.2.txt")
    return  findDistanceToIntersection(Int.MAX_VALUE, lists1, lists2)
}

private fun findDistanceToIntersection(
    closest: Int,
    lists1: Pair<List<Vector>, List<Vector>>,
    lists2: Pair<List<Vector>, List<Vector>>
): Int {
    val result1 = minDistanceToIntersect(lists1.second, lists2.first, closest)
    val result2 = minDistanceToIntersect(lists2.second, lists1.first, result1)
    return if(result1 < result2) result1 else result2
}

private fun minDistanceToIntersect(
    lists2:  List<Vector>,
    lists1: List<Vector>,
    closest: Int
): Int {
    var temp = closest
    lists2.forEach { y ->
        lists1.forEach { x ->
            val cross = intersect(
                x.start.first,
                x.start.second,
                x.end.first,
                x.end.second,
                y.start.first,
                y.start.second,
                y.end.first,
                y.end.second
            )
           temp = closestPart1(cross, temp)
        }
    }
    return temp
}

private fun closestPart1(cross: Pair<Int, Int>?, closest: Int): Int {
    if (cross != null && closest > abs(cross.first) + abs(cross.second))
        return  abs(cross.first) + abs(cross.second)
    return closest
}


fun day3part2() : Int {

    var closest : Int = Int.MAX_VALUE
    val lists1 = getVectors("src/pre2021.day3.txt/pre2021.day3.txt.1.txt")
    val lists2 = getVectors("src/pre2021.day3.txt/pre2021.day3.txt.2.txt")

    closest = findClosest(lists1.second, lists2.first, closest)
    closest = findClosest(lists2.second, lists1.first, closest)

    return closest
}

private fun findClosest(
    list1 : List<Vector>,
    list2 : List<Vector>,
    closest: Int
): Int {
    var temp = closest
    list1.forEach { y ->
        list2.forEach { x ->
            val cross = intersect(
                x.start.first,
                x.start.second,
                x.end.first,
                x.end.second,
                y.start.first,
                y.start.second,
                y.end.first,
                y.end.second
            )
          temp = closestPart2(cross, x, y, temp)
        }
    }
    return temp
}

private fun closestPart2(cross: Pair<Int, Int>?, x: Vector, y: Vector, closest: Int) : Int {
    var temp = closest
    if (cross != null) {
        val rollback = abs(cross.first - x.end.first) + abs(cross.second - y.end.second)
        val dist = (x.steps + y.steps) - rollback.toInt()
        if (closest > dist)
            temp = dist
    }
    return temp
}
