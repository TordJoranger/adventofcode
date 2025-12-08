package main.aoc2025.day8


import java.io.File
import kotlin.math.pow
import kotlin.math.sqrt

fun part1(input: File) : Long = findCircuits(input.readLines())

fun findCircuits(readLines: List<String>): Long {

 val orderedList = mutableListOf<Pair<Pair<Point, Point>, Double>>()


   val points = readLines.map {
        val coordinates = it.split(",")
        Point(coordinates[0].toDouble(),coordinates[1].toDouble(),coordinates[2].toDouble())
    }

    points.forEachIndexed { index1,point ->
       points.drop((1+index1)).forEachIndexed { index2, point2 ->
           val distance = distance(point, point2)
           if(orderedList.size < 10)
               orderedList.add(Pair(Pair(point,point2),distance))
           else if(distance < orderedList.last().second) {
               orderedList.removeLast()
               orderedList.add(Pair(Pair(point,point2),distance))
           }
           orderedList.sortBy { it.second }
       }
    }

    val circ =points.map { mutableSetOf(it) }.toMutableSet()

    (0 .. 9).forEach { i ->
        run {
            var added = false
            circ.forEach { list ->
                if (list.contains(orderedList[i].first.first)) {
                    list.add(orderedList[i].first.second)
                    added = true
                } else if (list.contains(orderedList[i].first.second)) {
                    list.add(orderedList[i].first.first)
                    added = true
                }
            }


        }
    }
val circuits = orderedList.map { it.first }.foldIndexed(mutableListOf(mutableSetOf(orderedList.first().first.first,orderedList.first().first.second))){ index,acc, triple ->
    var added = false

    if(index > 9){
        acc.add(mutableSetOf(triple.first))
        acc.add(mutableSetOf(triple.second))
    }

    if(index == 0) {
        acc
    }
    else{
        acc.forEach { list ->
        if(list.contains(triple.first)) {
            list.add(triple.second)
            added = true
        }
        else if(list.contains(triple.second)){
            list.add(triple.first)
            added = true
        } }
        if(!added) {
            acc.add(mutableSetOf(triple.first,triple.second))
        }
    acc
    }
}

    return 0L
}

fun findClosest(point: Point, others: List<Point>) : Pair<Point, Double> {

    val first = others.minBy { otherPoint -> distance(point, otherPoint) }
    return Pair(first,distance(point,first))

}

fun part2(input: File) : Long = findCircuits(input.readLines())

data class Point(val x: Double, val y: Double, val z : Double)


fun distance(pointA: Point, pointB : Point) : Double {
    return sqrt((pointA.x - pointB.x).pow(2) + (pointA.y - pointB.y).pow(2) + (pointA.z - pointB.z).pow(2))
}


